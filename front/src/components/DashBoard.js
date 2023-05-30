import React, {useEffect} from "react";
import "../style/dashboardstyles.css";

function DashBoard(){
    useEffect(() => {
        const script = document.createElement("script");
        script.src = "https://use.fontawesome.com/releases/v6.3.0/js/all.js";
        script.async = true;
        document.body.appendChild(script);
    });
    return (
        <div className="sb-nav-fixed">
        <nav className="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a className="navbar-brand ps-3" href="/"><img
                src="https://pds.saramin.co.kr/company/logo/202104/05/qr2abz_s6yv-j605m_logo.png" alt={"오렌지아이"} width="50"/></a>
        </nav>
        <br/>
        <div>
            <div id="layoutSidenav_content">
                <div className="container-fluid px-4">
                    <h1 className="mt-4">Dashboard</h1>
                    <ol className="breadcrumb mb-4">
                        <li className="breadcrumb-item active">실시간 모니터링</li>
                    </ol>
                    <br/><br/>
                    <div className="services">
                        <div className="service">주문 서비스</div>
                        <div className="service">결제 서비스</div>
                    </div>
                    <br/><br/>
                    <div className="row">
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-area me-1"></i>
                                    CPU usage - 주문서비스 jvm
                                </div>
                                <div className="card-body">
                                    <iframe
                                        src="http://localhost/d-solo/JrQc1xsmk/jvm-actuator-with-application-name?orgId=1&refresh=5s&theme=light&panelId=106"
                                        width="100%" height="200" frameBorder="0" title={'33 '}></iframe>
                                </div>
                            </div>
                        </div>
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-bar me-1"></i>
                                    CPU usage - 결제서비스 jvm
                                </div>
                                <div className="card-body">
                                    <iframe
                                        src="http://localhost/d-solo/JrQc1xsmk2/jvm-actuator-with-application-name2?orgId=1&refresh=5s&var-application=&var-instance=host.docker.internal%3A8081&var-jvm_memory_pool_heap=All&var-jvm_memory_pool_nonheap=All&theme=light&panelId=106"
                                        width="100%" height="200" frameBorder="0" title={'51'}></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-area me-1"></i>
                                    Http 응답 시간
                                </div>
                                <div className="card-body">
                                    <iframe
                                        src="http://localhost/d-solo/JrQc1xsmk/jvm-actuator-with-application-name?orgId=1&refresh=5s&theme=light&panelId=113"
                                        width="100%" height="200" frameBorder="0" title={'2521'}></iframe>
                                </div>
                            </div>
                        </div>
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-bar me-1"></i>
                                    Http 응답 시간
                                </div>
                                <div className="card-body">
                                    <iframe
                                        src="http://localhost/d-solo/JrQc1xsmk2/jvm-actuator-with-application-name2?orgId=1&refresh=5s&var-application=&var-instance=host.docker.internal%3A8081&var-jvm_memory_pool_heap=All&var-jvm_memory_pool_nonheap=All&theme=light&panelId=113"
                                        width="100%" height="200" frameBorder="0" title={'12'}></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-area me-1"></i>
                                    DML Monitoring
                                </div>
                                <div className="card-body">
                                    <iframe
                                        src="http://localhost/d-solo/v5ciIbUZz/postgresql-exporter?orgId=1&refresh=1m&theme=light&panelId=124"
                                        width="100%" height="200" frameBorder="0" title={'251'}></iframe>
                                </div>
                            </div>
                        </div>
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-bar me-1"></i>
                                    DML Monitoring
                                </div>
                                <div className="card-body">
                                    <iframe
                                        src="http://localhost/d-solo/v5ciIbUZz/postgresql-exporter?orgId=1&refresh=1m&theme=light&panelId=176"
                                        width="100%" height="200" frameBorder="0" title={'2214'}></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/><br/>
                    <div className="row">
                        <hr style={{border: '1px solid black', width: '100%'}} />
                    </div>
                    <br/><br/>
                    <div className="card mb-4">
                        <div className="card-header">
                            <i className="fas fa-table me-1"></i>
                            Node-exporter 수집 시간 분석 그래프
                        </div>
                        <div className="card-body">
                            <iframe
                                src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&refresh=5s&theme=light&panelId=40"
                                width="100%" height="400" frameBorder="0" title={'7'}></iframe>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-xl-3 col-md-6">
                        <div className="card mb-4">
                            <iframe
                                src="http://localhost/d-solo/v5ciIbUZz/postgresql-exporter?orgId=1&refresh=1m&theme=light&panelId=169"
                                width="450" height="200" frameBorder="0" title={'22a252'}></iframe>
                        </div>
                    </div>
                    <div className="col-xl-3 col-md-6">
                        <div className="card mb-4">
                            <iframe
                                src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&theme=light&panelId=16"
                                width="450" height="200" frameBorder="0" title={"a2"}></iframe>
                        </div>
                    </div>
                    <div className="col-xl-3 col-md-6">
                        <div className="card mb-4">
                            <iframe
                                src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&theme=light&panelId=20"
                                width="450" height="200" frameBorder="0" title={"3a"}></iframe>
                        </div>
                    </div>
                    <div className="col-xl-3 col-md-6">
                        <div className="card mb-4">
                            <iframe
                                src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&refresh=5s&theme=light&panelId=155"
                                width="450" height="200" frameBorder="0" title={'251'}></iframe>
                        </div>
                    </div>
                </div>
                <br/><br/>

                <footer className="py-4 bg-light mt-auto">
                    <div className="container-fluid px-4">
                        <div className="d-flex align-items-center justify-content-between small">
                            <div className="text-muted">Copyright &copy; Orangei 2023</div>
                            <div>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        </div>
    );
}



export default DashBoard;