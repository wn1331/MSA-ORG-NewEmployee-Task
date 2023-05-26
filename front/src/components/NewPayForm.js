import React, {useState} from 'react';
import {useLocation, useNavigate} from "react-router-dom";

function NewPayForm({orders}) {
    const navigate = useNavigate();
    const location = useLocation();
    const orderData = location.state;

    // const orderId = orderData.orderId;
    // const itemName = orderData.itemName;
    // const totalPrice = orderData.totalPrice;
    //
    // const [isSuccessModalOpen, setSuccessModalOpen] = useState(false);
    // const [isFailureModalOpen, setFailureModalOpen] = useState(false);

    return (
        <div>
            <header>
                <nav className="nav nav-pills pull-right">
                    <ul className="nav nav-pills pull-right">
                    </ul>
                </nav>
                <a href="/">
                    <h3 className="text-muted">ORG-I SHOP</h3>
                </a>
            </header>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th>주문 번호</th>
                    <th>상품 이름</th>
                    <th>상품 주문수량</th>
                    <th>상품 주문가격</th>
                    <th>주문 일시</th>
                    <th>결제 상태</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr key={orderData.orderId}>
                    <td>{orderData.orderId}</td>
                    <td>{orderData.itemName}</td>
                    <td>{orderData.count}</td>
                    <td>{orderData.totalPrice}원</td>
                    <td>{orderData.orderDate}</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}

export default NewPayForm;
