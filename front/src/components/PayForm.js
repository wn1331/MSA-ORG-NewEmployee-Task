import React, {useState} from 'react';
import {useLocation} from "react-router-dom";
import axios from "axios";

function PayForm({orders}) {
    const location = useLocation();
    const orderData = location.state;
    const orderId = orderData.orderId;
    const totalPrice = orderData.totalPrice;
    const [payStatus, setPayStatus] = useState(orderData.payStatus);
    const [isSuccessModalOpen, setSuccessModalOpen] = useState(false);
    const [isFailureModalOpen, setFailureModalOpen] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();

        // 주문 정보를 서버로 전송
        axios
            .post('/api2/v1/payments', {orderId, totalPrice, payStatus})
            .then((response) => {
                console.log('결제가 성공적으로 처리되었습니다.' + orderId + ' and ' + totalPrice);
                // 결제 성공 시 여기에 로직 쓰면 됨
                setSuccessModalOpen(true);
                setPayStatus(response.data.payStatus);
            })
            .catch((error) => {
                console.error('결제 처리 중 오류가 발생했습니다.', error);
                setFailureModalOpen(true);
                // 결제 실패 시 여기에 로직 쓰면 됨
                setPayStatus("결제 실패");
            });

    };

    return (
        <div>
            <header>
                <nav className="nav nav-pills pull-right">
                    <ul className="nav nav-pills pull-right">
                    </ul>
                </nav>
                <ul className="nav nav-pills pull-right">
                    <li><a href="/">Home</a></li>
                </ul>
                <h3 className="text-muted">주문서</h3>
            </header>
            <form onSubmit={handleSubmit}>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>주문 번호</th>
                        <th>상품 이름</th>
                        <th>상품 주문수량</th>
                        <th>상품 주문가격</th>
                        <th>주문 일시</th>
                        <th>결제 상태</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr key={orderId}>
                        <td>No.{orderId}</td>
                        <td>{orderData.itemName}</td>
                        <td>{orderData.count}개</td>
                        <td>{totalPrice}원</td>
                        <td>{orderData.orderDate}</td>
                        <td>{payStatus}</td>
                    </tr>
                    </tbody>
                </table>
                <div className="d-flex justify-content-end">
                    <button type="submit" className="btn btn-primary mr-2">주문하기</button>
                </div>
            </form>
        </div>
    );
}

export default PayForm;
