import React, {ReactNode} from 'react'
import styles from './genericPageLayout.module.scss'
import GenericPageHeader from "@/shared/components/generic-page-header/genericPageHeader";
import { PropsWithChildren } from 'react'

interface IGenericPageLayout{
    title: string
}
export default function AppLayout(props: PropsWithChildren<IGenericPageLayout>) {
    return (
        <div className={styles.genericPageLayoutConteiner}>
            <GenericPageHeader title={props.title}/>
            <main className={styles.mainContainer}>
                {props.children}
            </main>
        </div>
    )
}
