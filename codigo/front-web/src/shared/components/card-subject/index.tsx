import React, {useEffect, useState} from 'react'

import {ISubjectDTO} from '@/shared/domain/Subject/ISubject'
import styles from './style.module.scss'
import {AiFillStar} from 'react-icons/ai';
import {useRouter} from "next/router";
import Image from "next/image";

export default function CardSubject(props: ISubjectDTO) {
    const router = useRouter()

    const params = {
        subjectHashId: props.hashId
    };
    const subjectDetails = () => {
        router.push({
            pathname: `subject/${props.hashId}`,
        })
    }
    return (
        <div className={styles.cardContainer} onClick={subjectDetails}>
            <div className={styles.cardImageContainer}>
                <Image width={200} height={200} src={`${props.imageUrl}`} alt="" loading="eager" />
            </div>
            <div className={styles.cardContentContainer}>
                <div className={styles.primaryInfo}>
                    <div className={styles.titlesWrapper}>
                        <h3>{props.name}</h3>
                        <h4>{props.university}</h4>
                    </div>
                    <div className={styles.scoreWrapper}>
                        <div className={styles.scoreBox}>
                            <p className={styles.score}>
                                {props.score}
                            </p>
                            <p className={styles.starIcon}><AiFillStar/></p>
                        </div>
                    </div>
                </div>
                <div className={styles.secondaryInfo}>
                    <p>
                        {props.shortDescription}
                    </p>
                </div>
            </div>
        </div>
    )
}
