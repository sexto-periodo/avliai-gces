import React from 'react';
import styles from './subjectReview.module.scss'
import {ISubjectReview} from "@/shared/domain/Subject/ISubjectReview";
import Vote from "@/shared/components/subject-review/vote/vote";


interface ISubjectReviewCard{
    review: ISubjectReview,
}
export default function SubjectReview(props: ISubjectReviewCard){
    return(

      <div className={styles.subjectReviewContainer}>
          <div className={styles.header}>

              <div className={styles.headerLeft}>
                    <div className={styles.userImageContainer}>
                        <img src={props.review.reviewerImage} alt=""/>
                    </div>
                    <div className={styles.userInfoText}>
                        <h4>Usuário anonimo</h4>
                    </div>
              </div>

              <div className={styles.headerRight}>
                    <Vote
                        downvote={props.review.downvotedByUser}
                        upvote={props.review.upvotedByUser}
                        votes={props.review.votes}
                    />
              </div>
          </div>
          <div className={styles.content}>
              <div className={styles.reviewText}>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eu elementum dolor. Curabitur convallis, nibh nec volutpat blandit, elit sem rhoncus mauris, a eleifend ex arcu tincidunt diam. Etiam pharetra sit amet dui sed rutrum. Donec placerat magna nibh, quis maximus diam sollicitudin vel. Nulla vitae ultricies elit. Vivamus sit amet nisi eros. Integer porta sem nec dolor molestie malesuada. Cras sodales augue ante, sed lacinia turpis auctor in. Fusce pulvinar ipsum sit amet congue ullamcorper. Morbi lacinia mollis turpis, in tristique velit aliquet ut. Phasellus molestie sit amet felis ac commodo. Aliquam elementum tellus nunc, non ultrices eros luctus in.</p>

                  <p>Nullam vel semper sem. Mauris accumsan accumsan eros, ut eleifend eros eleifend sit amet. Aenean porttitor ante sed fermentum varius. Donec viverra vitae sapien a lacinia. Mauris consequat, neque ac facilisis gravida, metus augue sodales neque, a lacinia nibh velit sit amet ipsum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In turpis ipsum, iaculis quis vulputate eget, convallis vel elit. Nunc ultricies et neque vel luctus. Suspendisse sit amet accumsan metus.</p>
              </div>
          </div>
      </div>

    );
}