import React, {useState} from 'react';
import axios from 'axios';
import {useLocation} from "react-router-dom";

function PayForm() {
    const [product, setProduct] = useState('');
    const [paymentInfo, setPaymentInfo] = useState('');

    const location = useLocation();
    const orderData = location.state;

    const handleSubmit = (e) => {
        e.preventDefault();

        // 주문 정보를 서버로 전송
        axios.post('/api2/v1/payments', {product, paymentInfo})
            .then((response) => {
                console.log('결제가 성공적으로 처리되었습니다.');
                // 결제 성공 시 여기에 로직 쓰면됨
            })
            .catch((error) => {
                console.error('결제 처리 중 오류가 발생했습니다.', error);
                // 결제 실패 시 여기에 로직 쓰면 됨
            });

        // 폼 초기화
        setProduct('');
        setPaymentInfo('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <br/><br/><br/>
            <div>
                <label htmlFor="product">주문 번호:</label>
                <input
                    id="product"
                    value={orderData.orderId}
                    readOnly={orderData.orderId}
                    style={{ border: 'none', outline: 'none' }}
                />
            </div>
            <br/><br/><br/>
            <div>
                <label htmlFor="product">상품명:</label>
                <input
                    id="product"
                    value={orderData.itemName}
                    readOnly={orderData.itemName}
                    style={{ border: 'none', outline: 'none' }}
                />
            </div>
            <br/><br/><br/>
            <div>
                <label htmlFor="paymentInfo">결제 가격:</label>
                <input
                    type="text"
                    id="paymentInfo"
                    value={orderData.totalPrice}
                    readOnly={orderData.orderId}
                    style={{ border: 'none', outline: 'none' }}
                />
            </div>
            <br/><br/><br/>
            <div>
                <button type="submit">결제하기</button>
            </div>
        </form>
    );
};

export default PayForm;