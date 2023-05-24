import React from 'react';

class PayConfirmModal extends React.Component {
    render() {
        const { onClose, onConfirm } = this.props;

        return (
            <div className="modal">
                <div className="modal-content">
                    <h3>결제가 성공했습니다</h3>
                    {/* 모달 내용 추가 */}
                    <button onClick={onConfirm}>확인</button>
                </div>
            </div>
        );
    }
}