import React from  'react';
import styles from './genericPageHeader.module.scss'

interface IGenericPageHeader{
    title: string
}
export default function GenericPageHeader(props: IGenericPageHeader) {
    return (
        <div className={styles.genericPageHeaderContainer}>
            <h1>{props.title}</h1>
        </div>
    )
}
