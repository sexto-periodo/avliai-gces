import Head from 'next/head'
import React from 'react'
import Sidebar from "@/shared/components/sidebar";
import styles from './applayout.module.scss'


export default function AppLayout({ children }: any) {
    return (
        <div className={styles.layoutContainer}>
            <Sidebar/>
            <main className={styles.mainContainer}>
                {children}
            </main>
        </div>
    )
}
