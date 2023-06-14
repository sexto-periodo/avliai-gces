import React from 'react';

import styles from './sidebar.module.scss';
import {GoPencil} from 'react-icons/go';
import {GiHamburgerMenu} from 'react-icons/gi';
import {MdCommentBank, MdContactMail, MdFactCheck} from 'react-icons/md';
import {FaGraduationCap} from 'react-icons/fa'
import Item from './item/item'
import {ModalType} from "@/shared/components/modal/ModalEnum";
import Link from "next/link";
import {useRouter} from "next/router";

interface ISidebar {
    //openModal: Function
}

export default function Sidebar(props: ISidebar) {

    const router = useRouter();
    return (
        <aside className={styles.sidebarContainer}>


            {/*<div className={styles.sandwichMenuContainer}>*/}
            {/*    <span>*/}
            {/*        <GiHamburgerMenu/>*/}
            {/*    </span>*/}
            {/*</div>*/}


            <div className={styles.actionButtonContainer}>
                <button className={styles.actionButton} onClick={() => router.push('/')}>
                    <FaGraduationCap/>
                </button>
            </div>

            <div>
                <ul>
                    <li>
                        <Link href="/user" style={{textDecoration:'none'}}>
                            <Item label="Meu Perfil">
                                <MdCommentBank/>
                            </Item>
                        </Link>
                    </li>
                </ul>
            </div>
        </aside>
    );
}