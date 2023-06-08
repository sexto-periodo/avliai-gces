import React from 'react';

import styles from './sidebar.module.scss';
import {GoPencil} from 'react-icons/go';
import {GiHamburgerMenu} from 'react-icons/gi';
import {MdCommentBank, MdContactMail, MdFactCheck} from 'react-icons/md';
import Item from './item/item'
import {ModalType} from "@/shared/components/modal/ModalEnum";

interface  ISidebar{
    //openModal: Function
}
export default function Sidebar(props: ISidebar) {
    return (
        <aside className={styles.sidebarContainer}>


            {/*<div className={styles.sandwichMenuContainer}>*/}
            {/*    <span>*/}
            {/*        <GiHamburgerMenu/>*/}
            {/*    </span>*/}
            {/*</div>*/}


            {/*<div className={styles.actionButtonContainer}>*/}
            {/*    <button className={styles.actionButton}>*/}

            {/*        <GoPencil/>*/}

            {/*    </button>*/}
            {/*</div>*/}

            <div>
                <ul>
                    <li>
                        <Item label="Item 1">
                            <MdCommentBank/>
                        </Item>
                        <Item label="Item 1">
                            <MdContactMail/>
                        </Item>
                        <Item label="Item 1">
                            <MdFactCheck/>
                        </Item>

                    </li>
                </ul>
            </div>
        </aside>
    );
}