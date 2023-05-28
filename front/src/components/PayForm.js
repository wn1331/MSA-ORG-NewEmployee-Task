import React, {useState} from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import axios from "axios";

function PayForm({orders}) {
    const location = useLocation();
    const navigate = useNavigate();
    const orderData = location.state;
    const orderId = orderData.orderId;
    const totalPrice = orderData.totalPrice;
    const [payStatus, setPayStatus] = useState(orderData.payStatus);
    const [isSuccessModalOpen, setSuccessModalOpen] = useState(false);
    const [isFailureModalOpen, setFailureModalOpen] = useState(false);

    const handleModalConfirm = () => {
        setSuccessModalOpen(false);
        setFailureModalOpen(false);
        // 결제 성공 시 페이지 이동
        navigate('/');
    };


    const handleSubmit = (e) => {
        e.preventDefault();

        // 주문 정보를 서버로 전송
        axios
            .post('/api2/v1/payments', {orderId, totalPrice, payStatus})
            .then((response) => {
                console.log('결제가 성공적으로 처리되었습니다.' + orderId + ' and ' + totalPrice);
                setSuccessModalOpen(true);
                setPayStatus(response.data.payStatus);
            })
            .catch((error) => {
                console.error('결제 처리 중 오류가 발생했습니다.', error);
                setFailureModalOpen(true);
                setPayStatus("결제 실패");
            });
    };

    const renderModal = () => {
        if (isFailureModalOpen) {
            console.log("실패했습니다. 모달 띄우기 중");
            return (
                <div className="modal-overlay">
                    <div className="modal">
                        <div className="modal-content">
                            <h3>주문에 실패했습니다</h3>
                            <button onClick={handleModalConfirm}>확인</button>
                        </div>
                    </div>
                </div>
            );
        } else if (isSuccessModalOpen) {
            console.log("성공했습니다. 모달 띄우기 중");
            return (
                <div className="modal-overlay">
                    <div className="modal">
                        <div className="modal-content">
                            <h3>주문에 성공했습니다</h3>
                            <button onClick={handleModalConfirm}>확인</button>
                        </div>
                    </div>
                </div>
            );
        }
    }

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
            {renderModal()};
        </div>
    );
}

export default PayForm;
