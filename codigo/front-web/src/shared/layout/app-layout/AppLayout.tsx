import React, {useState} from 'react'
import Sidebar from "@/shared/components/sidebar";
import styles from './applayout.module.scss'
import {createPortal} from 'react-dom';
import Modal from '@/shared/components/modal/ModalLayout';
import {ModalType} from "@/shared/components/modal/ModalEnum";
import ReviewModal from '@/shared/components/modal/review-modal';
import {ToastContainer} from "react-toastify";


export default function AppLayout({children}: any) {


    return (
        <>
            <div className={styles.layoutContainer}>
                <Sidebar/>
                <main className={styles.mainContainer}>
                    {children}
                </main>
            </div>

        </>


    )
}
