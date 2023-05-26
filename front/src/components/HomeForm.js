import React from 'react';

function HomeForm() {
    return (
        <div className="container">
            <ul className="nav nav-pills pull-right">
                <li><a href="/">Home</a></li>
            </ul>
            <a href="/"><h3 className="text-muted">ORG-I SHOP</h3></a>
            <div className="jumbotron">
                <h1>ORG-I SHOP</h1>
                <p className="lead">주문 기능</p>
                <p>
                    <a className="btn btn-lg btn-info" href="/order">상품 주문</a>
                    <a className="btn btn-lg btn-info" href="/orders">주문 내역</a>
                </p>
            </div>

            <div className="footer">
                <p>ORG-I Shop</p>
            </div>
        </div>
    );
}

export default HomeForm;
