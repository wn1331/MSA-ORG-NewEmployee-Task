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
        <div id="layoutSidenav">
            <div id="layoutSidenav_content">
                <div className="container-fluid px-4">
                    <h1 className="mt-4">Dashboard</h1>
                    <ol className="breadcrumb mb-4">
                        <li className="breadcrumb-item active">Dashboard</li>
                    </ol>
                    <div className="row">
                        <div className="col-xl-3 col-md-6">
                            <div className="card mb-4">

                            </div>
                        </div>
                        <div className="col-xl-3 col-md-6">
                            <div className="card mb-4">

                            </div>
                        </div>
                        <div className="col-xl-3 col-md-6">
                            <div className="card mb-4">

                            </div>
                        </div>
                        <div className="col-xl-3 col-md-6">
                            <div className="card mb-4">

                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-area me-1"></i>
                                    Area Chart Example
                                </div>
                                <div className="card-body">iframe 여기 width="100%" height="40"</div>
                            </div>
                        </div>
                        <div className="col-xl-6">
                            <div className="card mb-4">
                                <div className="card-header">
                                    <i className="fas fa-chart-bar me-1"></i>
                                    Bar Chart Example
                                </div>
                                <div className="card-body">iframe 여기 width="100%" height="40"</div>
                            </div>
                        </div>
                    </div>
                    <div className="card mb-4">
                        <div className="card-header">
                            <i className="fas fa-table me-1"></i>
                            DataTable Example
                        </div>
                        <div className="card-body">
                            iframe 여기 width="100%" height="40"
                        </div>
                    </div>
                    <div className="card mb-4">
                        <div className="card-header">
                            <i className="fas fa-table me-1"></i>
                            DataTable Example
                        </div>
                        <div className="card-body">
                            iframe 여기 width="100%" height="40"
                        </div>
                    </div>
                    <div className="card mb-4">
                        <div className="card-header">
                            <i className="fas fa-table me-1"></i>
                            DataTable Example
                        </div>
                        <div className="card-body">
                            iframe 여기 width="100%" height="40"
                        </div>
                    </div>
                </div>
                <footer className="py-4 bg-light mt-auto">
                    <div className="container-fluid px-4">
                        <div className="d-flex align-items-center justify-content-between small">
                            <div className="text-muted">Copyright &copy; Your Website 2023</div>
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