import React from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';
import '../style/PayForm.css'; // Import the CSS file for the component

function PayForm() {
    const location = useLocation();
    const orderData = location.state;

    const orderId = orderData.orderId;
    const itemName = orderData.itemName;
    const totalPrice = orderData.totalPrice;

    const handleSubmit = (e) => {
        e.preventDefault();

        // 주문 정보를 서버로 전송
        axios
            .post('/api2/v1/payments', { orderId, itemName, totalPrice })
            .then((response) => {
                console.log('결제가 성공적으로 처리되었습니다.' + orderId + ' and ' + totalPrice);
                // 결제 성공 시 여기에 로직 쓰면 됨
            })
            .catch((error) => {
                console.error('결제 처리 중 오류가 발생했습니다.', error);
                // 결제 실패 시 여기에 로직 쓰면 됨
            });

        // 폼 초기화
    };

    return (
        <form onSubmit={handleSubmit}>
            <br />
            <br />
            <br />
            <div>
                <label htmlFor="orderNumber">주문 번호:</label>
                <input
                    id="orderNumber"
                    value={orderData.orderId}
                    readOnly={true}
                    className="input-field"
                />
            </div>
            <br />
            <br />
            <br />
            <div>
                <label htmlFor="productName">상품명:</label>
                <input
                    id="productName"
                    value={orderData.itemName}
                    readOnly={true}
                    className="input-field"
                />
            </div>
            <br />
            <br />
            <br />
            <div>
                <label htmlFor="paymentInfo">결제 가격:</label>
                <input
                    type="text"
                    id="paymentInfo"
                    value={orderData.totalPrice}
                    readOnly={true}
                    className="input-field"
                />
            </div>
            <br />
            <br />
            <br />
            <div>
                <button className="submit-button" type="submit">
                    결제하기
                </button>
            </div>
        </form>
    );
}

export default PayForm;
