import React, { useState } from 'react'
import {ISubjectDTO} from '@/shared/domain/Subject/ISubject'
import styles from './subjectDetails.module.scss'
import {AiFillStar} from "react-icons/ai";
import { BiDotsVerticalRounded } from 'react-icons/bi';

export default function SubjectDetails(props: ISubjectDTO) {

    return (
        <div className={styles.subjectDetailsContainer}>
                <div className={styles.header}>
                    <div className={styles.headerLeft}>
                        <h2>{props.name}</h2>
                        <h3>{props.university}</h3>
                        <div className={styles.scoreWrapper}>
                            <div className={styles.scoreBox}>
                                <p className={styles.score}>
                                    {props.score}
                                </p>
                                <p className={styles.starIcon}><AiFillStar/></p>
                            </div>
                        </div>
                    </div>
                    <div className={styles.headerRight}>
                        <div className={styles.pointMenuBox}>
                            <BiDotsVerticalRounded/>
                        </div>
                    </div>
                </div>
                <div className={styles.content}>

                    <div className={styles.coverImageContainer}>
                        <img src={props.imageUrl} alt=""/>
                    </div>


                    <h3>
                        Descrição
                    </h3>

                    <div className={styles.descriptionContainer}>
                        <p>
                            {props.longDescription}
                        </p>
                    </div>
                </div>

        </div>
    )
}
