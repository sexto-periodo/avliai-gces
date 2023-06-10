import React, {useEffect, useState} from 'react';
import styles from './subjectReview.module.scss'
import {
    IReviewByUserDTO,
    IReviewDTO
} from "@/shared/domain/Review/IReview";
import Vote from "@/shared/components/subject-review/vote/vote";
import Image from "next/image";


interface IReviewCard{
    review: IReviewByUserDTO | IReviewDTO,
}
export default function Review(props: IReviewCard){

    const [username, setUsername] = useState<string>("Usuário Anônimo");

    useEffect(() => {
        let byUser = props.review as IReviewByUserDTO;
        if(byUser.firstname){
            setUsername(`${byUser.firstname} ${byUser.lastname}`)
        }
    }, []);

    return(

      <div className={styles.subjectReviewContainer}>
          <div className={styles.header}>

              <div className={styles.headerLeft}>
                    <div className={styles.userImageContainer}>
                        <Image src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.RpAlj7580QO8X3YctCdTbgHaHa%26pid%3DApi&f=1&ipt=b32d13546c6157ac90d55fb68d2d6555c3a0ad35fdc50fbe5bfe2ffaabd90a20&ipo=images"
                               width={50}
                               height={50}
                               alt=""/>
                    </div>
                    <div className={styles.userInfoText}>
                        <h4>{username}</h4>
                    </div>
              </div>

              <div className={styles.headerRight}>
                    <Vote
                        downvote={true}
                        upvote={false}
                        votes={props.review.voteCount}
                    />
              </div>
          </div>
          <div className={styles.content}>
              <div className={styles.reviewText}>
                  {props.review.reviewText}
              </div>
          </div>
      </div>

    );
}