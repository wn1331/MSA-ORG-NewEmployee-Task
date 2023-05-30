import React, {useEffect, useState} from 'react';
import axios from "axios";
import {useNavigate} from "react-router-dom";
import "../style/OrderForm.css";

function OrderForm() {
    const [itemName, setSelectedProduct] = useState('');
    const [count, setQuantity] = useState('');
    const navigate = useNavigate();
    const [productOptions, setProductOptions] = useState([]);
    const [orderHistory, setOrderHistory] = useState([]); // New state for order history

    const handleListClick = (order) => (event) => {
        if(order.payStatus === "결제 실패") {
            alert("이미 결제 실패된 주문입니다. 다시 주문하세요.");
            return;
        }
        if(order.payStatus === "결제 완료") {
            alert("이미 결제 성공한 주문입니다.");
            return;
        }
        try{
            navigate('/payment', {state: order});
        }catch(error){
            console.error(error);
        }
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
                alert("주문에 실패했습니다.");
            }
        }

        fetchProductOptions().then(r => {});

        async function fetchOrderHistory() {
            try {
                const response = await axios.get('/api1/v1/orders');
                const orders = response.data;
                setOrderHistory(orders);
                console.log(orders);
            }catch (error) {
                console.error(error);
                alert("주문내역 불러오기 실패");
            }
        }
        fetchOrderHistory().then(r => {});

    }, []);

    const handleSubmit = async (event) => {
        event.preventDefault();
        if(itemName === '') {
            alert("주문 상품을 선택하세요.");
            return;
        }
        if (count <= 0) {
            alert("0개 이하는 주문할 수 없습니다.");
            return;
        }
        try {
            const response = await axios.post('/api1/v1/orders', {itemName, count});
            const orderData = response.data;
            navigate('/payment', {state: orderData});
        } catch (error) {
            console.error(error);
            console.log("주문에 실패했습니다.")
            alert("주문에 실패했습니다.");
        }
    };

    return (
        <div>
            <header className="container">
                <nav className="nav nav-pills pull-right">
                    <ul className="nav nav-pills pull-right">
                        <li><a href="/">Home</a></li>
                    </ul>
                </nav>
                <a href="/">
                    <h3 className="text-muted">ORG-I SHOP</h3>
                </a>
            </header>
            <div className="container jumbotron">
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

            {/* Order History */}
            <div className="order-history list-container">
                <h2>주문내역</h2>
                {orderHistory.length > 0 ? (
                    <ul>
                        {orderHistory.map((order, index) => (
                        <button type="button" className="button-link" key={index} onClick={handleListClick(order)}>
                            <li className={"li-flex"}>
                                <p className={"listItem"}>주문번호: {order.orderId}</p>
                                <p className={"listItem"}>상품명: {order.itemName}</p>
                                <p className={"listItem"}>주문수량: {order.count}</p>
                                <p className={"listItem"}>가격: {order.totalPrice}</p>
                                <p className={"listItem"}>주문일자: {order.orderDate}</p>
                                <p className={"listItem"}>주문상태: {order.payStatus}</p>
                            </li>
                        </button>
                        ))}
                    </ul>
                ) : (
                    <p>주문이 존재하지 않습니다..</p>
                )}
            </div>


            <div className="footer">
                <p>ORG-I Shop</p>
            </div>
            {/*<button type="button" onClick={handleDashboard}>123</button>*/}
        </div>
    );
}

export default OrderForm;
