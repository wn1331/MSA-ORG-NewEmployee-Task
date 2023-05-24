import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../style/OrderForm.css'; // Import the CSS file for the component

function OrderForm() {
    const [itemName, setSelectedProduct] = useState('');
    const [count, setQuantity] = useState('');
    const navigate = useNavigate();
    const [productOptions, setProductOptions] = useState([]);

    const handleProductChange = (event) => {
        setSelectedProduct(event.target.value);
    };

    const handleQuantityChange = (event) => {
        setQuantity(event.target.value);
    };

    useEffect(() => {
        async function fetchProductOptions() {
            try {
                const response = await axios.get('/api1/v1/items');
                const items = response.data;
                const options = items.map((item) => ({
                    value: item.itemName,
                    label: `${item.itemName} - 재고 ${item.stockQuantity}`,
                }));
                setProductOptions(options);
            } catch (error) {
                console.error(error);
            }
        }
        fetchProductOptions();
    }, []);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('/api1/v1/orders', { itemName, count });
            const orderData = response.data;
            navigate('/payment', { state: orderData });
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <br /><br/><br/>
            <label>
                주문 상품:
                <select className="select-box" value={itemName} onChange={handleProductChange}>
                    <option value="">상품을 선택하세요</option>
                    {productOptions.map((option) => (
                        <option key={option.value} value={option.value}>
                            {option.label}
                        </option>
                    ))}
                </select>
            </label>
            <br /><br/><br/>
            <label>
                주문 수량:
                <input className="quantity-input" type="number" value={count} onChange={handleQuantityChange} />
            </label>
            <br /><br/><br />
            <button className="submit-button" type="submit">주문하기</button><br /><br/><br /><br /><br/><br />

            <h1>CPU</h1>
            <iframe
                src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&from=1684901537318&to=1684901837319&refresh=5s&panelId=77"
                width="450" height="200" frameBorder="0" title={"grafana-cpu"}></iframe>
            <br/>
            <h1>Node-exporter Scrape Time</h1>
            <iframe
                src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&from=1684901542285&to=1684902442285&panelId=40"
                width="450" height="200" frameBorder="0" title={"Node-exporter Scrape Time"}></iframe>

        </form>
    );
}

export default OrderForm;
