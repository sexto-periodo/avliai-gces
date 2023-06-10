import GenericPageLayout from '@/shared/layout/generic-page-layout/genericPageLayout'
import React, {useEffect, useState} from 'react'
import styles from './user.module.scss'
import {IUser, IUserUpdateDataForm} from "@/shared/domain/User/User";
import {UserService} from "@/shared/domain/User/UserService";
import Image from 'next/image';
import {ReviewService} from "@/shared/domain/Review/ReviewService";
import {IReviewDTO} from "@/shared/domain/Review/IReview";
import SubjectReview from "@/shared/components/subject-review";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {Grid} from "@mui/material";
import Link from "next/link";
import {ISignUpForm} from "@/pages/auth/signup";
import {toast, ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import Masonry from "@mui/lab/Masonry";

export default function Home() {

    const userService: UserService = new UserService();
    const subjectReviewService: ReviewService = new ReviewService();

    const [user, setUser] = useState<IUser>({} as IUser);
    const [userFormData, setUserFormData] = useState<IUserUpdateDataForm>({
        firstname: "",
        lastname: "",
        profilePhotoUrl: "",
    })
    const [userReviews, setUserReviews] = useState<IReviewDTO[]>([] as IReviewDTO[]);

    useEffect(() => {
        let user: IUser = userService.getUserData() as IUser
        setUserFormData({
            firstname: user.firstname,
            lastname: user.lastname,
            profilePhotoUrl: user.profilePhotoUrl
        })
        setUser(user)
        subjectReviewService.getReviewsByUser().then(r => setUserReviews(r));
    }, []);

    function feedbackAlertResult(result: boolean) {
        if (result) {
            toast.success('Dados atualizados com sucesso.', {
                position: "top-right",
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "colored",
            });
            return
        }

        toast.error('Erro ao atualizar os dados, tente novamente mais tarde.', {
            position: "top-right",
            autoClose: 3000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "colored",
        });
    }


    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        try {
            await userService.updateUserData(userFormData)
                .then((user) => {
                        if (user) {
                            setUser(user)
                            feedbackAlertResult(true);
                            return
                        }
                        feedbackAlertResult(false);
                    }
                );
        } catch (err) {
            console.log(err)
        }
    };


    return (
        <>
            <GenericPageLayout title="Perfil">
                <div className={styles.mainUserContainer}>
                    <div className={styles.userDataContainer}>
                        <div className={styles.profilePhotoContainer}>
                            <Image width={400} height={400} src={user.profilePhotoUrl}
                                   alt="" loading="eager"/>
                        </div>
                        <div className={styles.userInfoContainer}>
                            <div className={styles.formContainer}>

                                <form
                                    className={styles.form}
                                    onSubmit={handleSubmit}
                                >
                                    <TextField
                                        sx={{m: 1, width: '40ch', mt: 3}}
                                        type="text"
                                        id="outlined-basic"
                                        label="Nome"
                                        variant="outlined"
                                        value={userFormData.firstname}
                                        onChange={(event) => setUserFormData({
                                            ...userFormData,
                                            firstname: event.target.value
                                        })}
                                    />
                                    <TextField
                                        sx={{m: 1, width: '40ch'}}
                                        type="text"
                                        id="outlined-basic"
                                        label="Sobrenome"
                                        variant="outlined"
                                        value={userFormData.lastname}
                                        onChange={(event) => setUserFormData({
                                            ...userFormData,
                                            lastname: event.target.value
                                        })}
                                    />
                                    <TextField
                                        sx={{m: 1, width: '40ch'}}
                                        type="text"
                                        id="outlined-basic"
                                        label="URL da Foto de Perfil"
                                        variant="outlined"
                                        value={userFormData.profilePhotoUrl}
                                        onChange={(event) => setUserFormData({
                                            ...userFormData,
                                            profilePhotoUrl: event.target.value
                                        })}
                                    />
                                    <Button variant="contained"
                                            disableElevation
                                            type="submit"
                                            sx={{ml: 1, mr: 1, mt: 3}}
                                            fullWidth={true}
                                    >
                                        Salvar
                                    </Button>
                                </form>
                            </div>

                        </div>
                    </div>
                    <div>
                        <h2>Minhas avaliações</h2>
                    </div>
                    <div className={styles.userReviewsContainer}>
                        <Masonry
                            columns={{xs: 1, sm: 2, md: 2, lg: 3, xl: 4}}
                            spacing={2}
                        >
                        {
                            userReviews.map((item, key) => {
                                    return (
                                            <SubjectReview key={key} review={item} />
                                    )
                                }
                            )
                        }
                        </Masonry>
                    </div>
                </div>

            </GenericPageLayout>
            <ToastContainer
                position="top-right"
                autoClose={3000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="colored"
            />

        </>
    )
}
