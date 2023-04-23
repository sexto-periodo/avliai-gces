import React from 'react';
import styles from './subjectRequestCard.module.scss'
import {BiDotsVerticalRounded} from "react-icons/bi";



export interface IUserProfileSubjectRequestCard{
    username: string,
    userUniversity: string,
    userProfilePhoto: string
}
export interface ISubjectRequestCard {
    userProfile: IUserProfileSubjectRequestCard,
    subjectCoverImage:string
    subjectTitle: string,
    subjectSubtitle: string,
    subjectShortDescription: string,

}

const UserProfile = (props: IUserProfileSubjectRequestCard) => {
    return(
        <div className={styles.userProfileContainer}>
            <div className={styles.userProfileImageContainer}>
                <img src={props.userProfilePhoto} alt=""/>
            </div>
            <div className={styles.userInfoBox}>
                <p>{props.username}</p>
                <p>{props.userUniversity}</p>
            </div>
        </div>
    )
}
export default function SubjectRequestCard(props: ISubjectRequestCard) {
    return (
        <div className={styles.cardContainer}>
            <div className={styles.cardHeader}>
                <div className={styles.headerLeft}>
                    <UserProfile
                        username={props.userProfile.username}
                        userProfilePhoto={props.userProfile.userProfilePhoto}
                        userUniversity={props.userProfile.userUniversity}
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