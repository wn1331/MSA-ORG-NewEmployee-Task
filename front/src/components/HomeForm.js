import React from 'react';
import {useNavigate} from "react-router-dom";


function HomeForm() {
    const navigate = useNavigate();


    const handleDashboardNavigate =() =>{
        navigate("/dashboard");
    };
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
                    <button type="button" onClick={handleDashboardNavigate}>모니터링</button>
                </p>
            </div>

            <div className="footer">
                <p>ORG-I Shop</p>
            </div>
        </div>
    );
}

export default HomeForm;
