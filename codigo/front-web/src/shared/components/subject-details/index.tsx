import React, { useState } from 'react'
import {ISubject} from '@/shared/models/ISubject'
import styles from './subjectDetails.module.scss'
import {AiFillStar} from "react-icons/ai";
import { BiDotsVerticalRounded } from 'react-icons/bi';

interface ISubjectDetails {
    subject: ISubject
}

export default function SubjectDetails(props: ISubjectDetails) {

    return (
        <div className={styles.subjectDetailsContainer}>
                <div className={styles.header}>
                    <div className={styles.headerLeft}>
                        <h2>{props.subject.name}</h2>
                        <h3>{props.subject.university}</h3>
                        <div className={styles.scoreWrapper}>
                            <div className={styles.scoreBox}>
                                <p className={styles.score}>
                                    {props.subject.score}
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
                        <img src={props.subject.imageUrl} alt=""/>
                    </div>


                    <h3>
                        Descrição
                    </h3>

                    <div className={styles.descriptionContainer}>
                        <p>
                            {props.subject.longDescription}
                        </p>
                    </div>
                </div>

        </div>
    )
}
