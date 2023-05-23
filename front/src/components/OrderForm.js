import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';

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
        //네비게이션 제거하고 아래거 주석해제
        // navigate("/payment");
        //아래거 잠깐 주석처리 함
        try {
            const response = await axios.post('/api1/v1/orders', {itemName, count});
            const orderData = response.data;
            navigate("/payment", {state: orderData});
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
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
            <br/>
            <label>
                주문 수량:
                <input  type="number" value={count} onChange={handleQuantityChange}/>
            </label>
            <br/>
            <button type="submit">주문하기</button>
        </form>
    );
}

export default OrderForm;

