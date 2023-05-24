import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../style/OrderForm.css'; // Import the CSS file for the component
import '../style/modal.css';

function OrderForm() {
    const [itemName, setSelectedProduct] = useState('');
    const [count, setQuantity] = useState('');
    const navigate = useNavigate();
    const [productOptions, setProductOptions] = useState([]);
    const [isFailureModalOpen, setFailureModalOpen] = useState(false);
    const handleModalConfirm = () => {
        setFailureModalOpen(false);

    };
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
            console.log("주문에 실패했습니다.")
            setFailureModalOpen(true);
        }
    };

    return (
        <div>
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
            </form>
            <h1>CPU</h1>
            <iframe
                src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&from=1684907635108&to=1684907935108&refresh=5s&theme=light&panelId=3"
                width="450" height="200" frameBorder="0" title={"cpu"}></iframe>
            <br/>
            <h1>Node-exporter Scrape Time</h1>
            <iframe
                src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&from=1684907459588&to=1684907759588&refresh=5s&theme=light&panelId=40"
                width="450" height="200" frameBorder="0" title={"Node-exporter"}></iframe>
            {isFailureModalOpen && (
                <div className="modal-overlay">
                    <div className="modal">
                        <div className="modal-content">
                            <h3>주문에 실패했습니다</h3>
                            {}
                            <button onClick={handleModalConfirm}>확인</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default OrderForm;
