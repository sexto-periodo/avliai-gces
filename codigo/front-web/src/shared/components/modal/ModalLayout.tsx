import React, { PropsWithChildren } from 'react'
import styles from './modalLayout.module.scss'



export default function ModalLayout({ children }:any) {
    return (
        <div className={styles.modalOverLay}>
            <div className={styles.modalLayoutContainer}>
                {children}
            </div>
        </div>

    )
}