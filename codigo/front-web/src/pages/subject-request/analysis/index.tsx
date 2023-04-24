import GenericPageLayout from '@/shared/layout/generic-page-layout/genericPageLayout'
import {TextField} from '@mui/material'
import React from 'react'
import styles from './subjectAnalysis.module.scss'
import {IUserProfileChip, UserProfileChip} from "@/shared/components/user-profile-chip";


const userProfileData: IUserProfileChip = {

    username: "Tai Lung",
    userProfilePhoto: "https://images6.fanpop.com/image/photos/33700000/Tai-Lung-in-Kung-Fu-Panda-Legends-of-Awesomeness-tai-lung-33701701-768-432.png",
    userUniversity: "Prisão Shogun"

}
export default function SubjectAnalysis() {
    return (
        <GenericPageLayout title="Aprovação de nova disciplina">
            <div className={styles.subjectAnalysisContainer}>
                <div className={styles.formContainer}>

                    <div className={styles.leftFormSide}>


                        <div className={styles.formQ1}>
                            <TextField
                                helperText="Nome da disciplina"
                                id="demo-helper-text-misaligned"
                                label="Nome"
                            />
                            <TextField
                                helperText="Período ao qual a disciplina pertence"
                                id="demo-helper-text-misaligned"
                                label="Período"
                            />
                            <TextField
                                helperText="Carga horária da disciplina"
                                id="demo-helper-text-misaligned"
                                label="Carga horária"
                            />
                            <TextField
                                helperText="Instituição de ensino"
                                id="demo-helper-text-misaligned"
                                label="Instituição"
                            />
                        </div>

                        <div className={styles.formQ3}>
                            <TextField
                                helperText="Forneça uma breve descrição da disciplina"
                                id="demo-helper-text-misaligned"
                                label="Descrição curta"
                                multiline
                                rows={6}
                            />
                            <TextField
                                helperText="Forneça o máximop de detalhes possivel sobre a disciplina"
                                id="demo-helper-text-misaligned"
                                label="Descrição"
                                multiline
                                rows={12}
                            />
                        </div>
                    </div>

                    <div className={styles.rightFormSide}>

                        <div className={styles.formQ2}>
                            <TextField
                                helperText="Forneça a ementa da disciplina"
                                id="demo-helper-text-misaligned"
                                label="Ementa"
                                multiline
                                rows={6}
                                fullWidth
                            />
                        </div>

                        <div className={styles.formQ4}>
                            <h3>Imagem de Capa</h3>
                            <TextField
                                helperText="Forneça a url de uma imagem"
                                id="demo-helper-text-misaligned"
                                label="Imagem de capa"
                                fullWidth
                            />
                            <div className={styles.imageContainer}>
                                <img src="" alt=""/>
                            </div>
                        </div>

                    </div>
                </div>
                <div className={styles.operationPanelContainer}>
                    <h3>Disciplina sugerida por:</h3>
                    <div className={styles.userProfileInfoContainer}>
                        <UserProfileChip
                            username={userProfileData.username}
                            userProfilePhoto={userProfileData.userProfilePhoto}
                            userUniversity={userProfileData.userUniversity}
                            imageSize={50}
                            userEmail="Email@ermail.com.br"
                        />
                    </div>
                    <div className={styles.approveReproveContainer}>

                        <button className={styles.reproveButton}>
                            Reprovar sugestão
                        </button>

                        <button className={styles.approveButton}>
                            Aprovar sugestão
                        </button>
                    </div>
                </div>
            </div>
        </GenericPageLayout>
    )
}
