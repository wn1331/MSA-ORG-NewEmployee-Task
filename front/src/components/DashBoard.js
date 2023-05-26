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
        <body className="sb-nav-fixed">
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
                    <div className="row">
                        <div className="col-xl-3 col-md-6">
                            <div className="card mb-4">
                                <iframe
                                    src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&theme=light&panelId=20"
                                    width="450" height="200" frameBorder="0" title={"cpu"}></iframe>
                            </div>
                        </div>
                        <div className="col-xl-3 col-md-6">
                            <div className="card mb-4">
                                <iframe
                                    src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&theme=light&panelId=16"
                                    width="450" height="200" frameBorder="0" title={"ram"}></iframe>
                            </div>
                        </div>
                        <div className="col-xl-3 col-md-6">
                            <div className="card mb-4">
                                <iframe
                                    src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&theme=light&panelId=16"
                                    width="450" height="200" frameBorder="0" title={"disk"}></iframe>
                            </div>
                        </div>
                        <div className="col-xl-3 col-md-6">
                            <div className="card mb-4">
                                <iframe
                                    src="http://localhost/d-solo/rYdddlPWk/node-exporter-full?orgId=1&theme=light&panelId=16"
                                    width="450" height="200" frameBorder="0" title={"netstat"}></iframe>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-area me-1"></i>
                                    JDBC Connection
                                </div>
                                <div className="card-body"><iframe
                                    src="http://localhost/d-solo/wdV6wx7iz/spring-boot-hikaricp-jdbc?orgId=1&refresh=5s&var-application=payment-service&var-region=All&var-instance=All&var-jdbc_connection_name=dataSource&var-hikaricp_pool_name=payment-hikari-pool&theme=light&panelId=10"
                                    width="100%" height="400" frameBorder="0" title={"mini1"}></iframe></div>
                            </div>
                        </div>
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-bar me-1"></i>
                                    HikariCP Connection
                                </div>
                                <div className="card-body"><iframe
                                    src="http://localhost/d-solo/wdV6wx7iz/spring-boot-hikaricp-jdbc?orgId=1&refresh=5s&var-application=payment-service&var-region=All&var-instance=All&var-jdbc_connection_name=dataSource&var-hikaricp_pool_name=payment-hikari-pool&theme=light&panelId=10"
                                    width="100%" height="400" frameBorder="0" title={"mini2"}></iframe></div>
                            </div>
                        </div>
                    </div>
                    <div className="card mb-4">
                        <div className="card-header">
                            <i className="fas fa-table me-1"></i>
                            Network Status
                        </div>
                        <div className="card-body">
                            <iframe
                                src="http://localhost/d-solo/wdV6wx7iz/spring-boot-hikaricp-jdbc?orgId=1&refresh=5s&var-application=payment-service&var-region=All&var-instance=All&var-jdbc_connection_name=dataSource&var-hikaricp_pool_name=payment-hikari-pool&theme=light&panelId=10"
                                width="100%" height="400" frameBorder="0" title={"hikariCP"}></iframe>
                        </div>
                    </div>
                    <div className="card mb-4">
                        <div className="card-header">
                            <i className="fas fa-table me-1"></i>
                            example chart1
                        </div>
                        <div className="card-body">
                            <iframe
                                src="http://localhost/d-solo/wdV6wx7iz/spring-boot-hikaricp-jdbc?orgId=1&refresh=5s&var-application=payment-service&var-region=All&var-instance=All&var-jdbc_connection_name=dataSource&var-hikaricp_pool_name=payment-hikari-pool&theme=light&panelId=10"
                                width="100%" height="400" frameBorder="0" title={"JDBCPool"}></iframe>
                        </div>
                    </div>
                    <div className="card mb-4">
                        <div className="card-header">
                            <i className="fas fa-table me-1"></i>
                            Example chart2
                        </div>
                        <div className="card-body">
                            <iframe
                                src="http://localhost/d-solo/wdV6wx7iz/spring-boot-hikaricp-jdbc?orgId=1&refresh=5s&var-application=payment-service&var-region=All&var-instance=All&var-jdbc_connection_name=dataSource&var-hikaricp_pool_name=payment-hikari-pool&theme=light&panelId=10"
                                width="100%" height="500" frameBorder="0" title={"Node-exporter"}></iframe>
                        </div>
                    </div>
                </div>
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
        </body>
    );
}

export default DashBoard;