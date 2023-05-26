import React, {useEffect, useState} from 'react';
import axios from "axios";
import {useNavigate} from "react-router-dom";

function OrderForm() {
    const [itemName, setSelectedProduct] = useState('');
    const [count, setQuantity] = useState('');
    const navigate = useNavigate();
    const [productOptions, setProductOptions] = useState([]);
    const handleDashboard = (event) => {
        navigate('/dashboard');
    }

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
            const response = await axios.post('/api1/v1/orders', {itemName, count});
            const orderData = response.data;
            navigate('/payment', {state: orderData});
        } catch (error) {
            console.error(error);
            console.log("주문에 실패했습니다.")
        }
    };

    return (
        <div className="container">
            <header>
                <nav className="nav nav-pills pull-right">
                    <ul className="nav nav-pills pull-right">
                        <li><a href="/">Home</a></li>
                    </ul>
                </nav>
                <a href="/">
                    <h3 className="text-muted">ORG-I SHOP</h3>
                </a>
            </header>
            <div className="jumbotron">
                <h1>상품 주문</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="item">상품명</label>
                        <select name="itemId" id="item" className="form-control" value={itemName}
                                onChange={handleProductChange}>
                            <option value="">상품선택</option>
                            {productOptions.map((option) => (
                                <option key={option.value} value={option.value}>
                                    {option.label}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="form-group">
                        <label htmlFor="count">주문수량</label>
                        <input type="number" name="count" className="form-control" id="count"
                               placeholder="주문 수량을 입력하세요" value={count} onChange={handleQuantityChange}/>
                    </div>

                    <button type="submit" className="btn btn-primary">주문하기</button>
                </form>
            </div>

            <div className="footer">
                <p>ORG-I Shop</p>
            </div>
            <button type="button" onClick={handleDashboard}>123</button>
        </div>
    );
}

export default OrderForm;
