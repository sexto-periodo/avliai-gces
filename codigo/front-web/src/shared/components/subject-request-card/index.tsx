import React from 'react';
import styles from './subjectRequestCard.module.scss'
import {BiDotsVerticalRounded} from "react-icons/bi";
import {
    IUserProfileChip,
    UserProfileChip
} from "@/shared/components/user-profile-chip";




export interface ISubjectRequestCard {
    userProfile: IUserProfileChip,
    subjectCoverImage:string
    subjectTitle: string,
    subjectSubtitle: string,
    subjectShortDescription: string,

}


export default function SubjectRequestCard(props: ISubjectRequestCard) {
    return (
        <div className={styles.cardContainer}>
            <div className={styles.cardHeader}>
                <div className={styles.headerLeft}>
                    <UserProfileChip
                        username={props.userProfile.username}
                        userProfilePhoto={props.userProfile.userProfilePhoto}
                        userUniversity={props.userProfile.userUniversity}
                        imageSize={40}
                    />
                </div>
                <div className={styles.headerRight}>
                    <div className={styles.menuBox}>
                        <BiDotsVerticalRounded/>
                    </div>
                </div>
            </div>
            <div className={styles.cardImageContainer}>
                <img src={props.subjectCoverImage} alt=""/>
            </div>
            <div className={styles.cardContentContainer}>
                <div className={styles.primaryContent}>
                    <h3>{props.subjectTitle}</h3>
                    <h4>{props.subjectSubtitle}</h4>
                </div>
                <div className={styles.secondaryContent}>
                    <p>{props.subjectShortDescription}</p>
                </div>
                <div className={styles.buttonContainer}>
                    <button>
                        Analisar
                    </button>
                </div>
            </div>
        </div>
    );
}