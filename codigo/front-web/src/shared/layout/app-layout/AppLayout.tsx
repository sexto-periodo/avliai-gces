import React, {useState} from 'react'
import Sidebar from "@/shared/components/sidebar";
import styles from './applayout.module.scss'
import {createPortal} from 'react-dom';
import Modal from '@/shared/components/modal/ModalLayout';
import {ModalType} from "@/shared/components/modal/ModalEnum";
import ReviewModal from '@/shared/components/modal/review-modal';

interface IModalControl {
    show: boolean
    modalType: ModalType
}

export default function AppLayout({children}: any) {

    const [showModal, setShowModal] = useState<IModalControl>({
        show: false,
        modalType: ModalType.REVIEW
    });

    function openModal(modalType: ModalType) {
        setShowModal({show: true, modalType: modalType});
    }

    function closeModal() {
        console.log("Fechando Modal")
        setShowModal({show: false, modalType: ModalType.REVIEW})
    }

    function buildModal() {

        switch (showModal.modalType) {
            case ModalType.REVIEW:
                return <ReviewModal onClose={closeModal}/>
            default:
                return null;
        }
    }

    return (
        <>
            <div className={styles.layoutContainer}>
                <Sidebar openModal={openModal}/>
                <main className={styles.mainContainer}>
                    {children}
                </main>
            </div>
            {showModal.show && createPortal(
                <Modal>
                    {
                        buildModal()
                    }
                </Modal>,
                document.body
            )}
        </>


    )
}
