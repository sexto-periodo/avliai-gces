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
    subjectCoverImage: "https://s2.glbimg.com/exBLrKtkDLLGNrEaO84KMGKkeB8=/0x0:1451x968/1008x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2019/Z/D/QhAd4ZTbmaRbbeNirkkQ/imagem-release-1683514.jpg",
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
