import React from 'react';

import styles from './item.module.scss';
import { PropsWithChildren } from 'react'

interface IItemSidebar{
    label: string
}
export default function item( props: PropsWithChildren<IItemSidebar>) {
    return (
        <div className={styles.itemContainer}>

            <div className={styles.iconContainer}>
                { props.children }
            </div>
            <div className={styles.labelContainer}>
                {props.label}
            </div>

        </div>
    );
}