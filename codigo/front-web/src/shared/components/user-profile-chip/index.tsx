import styles from './userProfileChip.module.scss'
import React from "react";


export interface IUserProfileChip {
    username: string,
    userUniversity: string,
    userProfilePhoto: string,
    userEmail?: string,
    imageSize?: 40 | 50
}

export const UserProfileChip = (props: IUserProfileChip) => {

    return (
        <div>
            <div className={styles.userProfileContainer}>
                <div className={styles.userProfileImageContainer}
                     style={{width: props.imageSize, height: props.imageSize}}>
                    <img src={props.userProfilePhoto} alt=""/>
                </div>
                <div className={styles.userInfoBox}>
                    <p>{props.username}</p>
                    <p>{props.userUniversity}</p>
                </div>
            </div>
            { props.userEmail ? <p>{props.userEmail}</p> : null
            }
        </div>

    )
}