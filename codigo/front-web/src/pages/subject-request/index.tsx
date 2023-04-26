import SubjectRequestCard, {
    ISubjectRequestCard
} from '@/shared/components/subject-request-card'
import GenericPageLayout from '@/shared/layout/generic-page-layout/genericPageLayout'
import React from 'react'
import styles from './subjectRequests.module.scss'


const request: ISubjectRequestCard = {
    userProfile: {
        username: "Tai Lung",
        userProfilePhoto: "https://images6.fanpop.com/image/photos/33700000/Tai-Lung-in-Kung-Fu-Panda-Legends-of-Awesomeness-tai-lung-33701701-768-432.png",
        userUniversity: "Prisão Shogun"
    },
    subjectShortDescription: " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris malesuada euismod egestas. ",
    subjectTitle: "Alguma disciplina",
    subjectCoverImage: "https://www.shutterstock.com/image-photo/young-african-american-man-using-260nw-2064750014.jpg",
    subjectSubtitle: "Subtitulo da disciplina"
}
export default function SubjectRequests() {
    return (
        <GenericPageLayout title="Requisições de disciplinas">
            <div className={styles.subjectRequestsCardsContainer}>
                <SubjectRequestCard {...request}/>
                <SubjectRequestCard {...request}/>
                <SubjectRequestCard {...request}/>
                <SubjectRequestCard {...request}/>
            </div>
        </GenericPageLayout>
    )
}
