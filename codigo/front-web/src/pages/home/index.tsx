import GenericPageLayout from '@/shared/layout/generic-page-layout/genericPageLayout'
import React, {useEffect, useState} from 'react'
import CardSubject from '../../shared/components/card-subject';
import Masonry from '@mui/lab/Masonry';

import styles from './home.module.scss'
import {ISubjectDTO} from "@/shared/domain/Subject/ISubject";
import {FormControl, InputAdornment, InputLabel, OutlinedInput} from '@mui/material';
import {ImSearch} from 'react-icons/im'
import {Subject} from "rxjs";
import {SubjectService} from "@/shared/domain/Subject/SubjectService";
import {UserService} from '@/shared/domain/User/UserService';
import {IUser} from '@/shared/domain/User/User';
import {IUniversityDTO} from "@/shared/domain/University/IUniversity";
import {UniversityService} from "@/shared/domain/University/UniversityService";

export default function Home() {

    const subjectService: SubjectService = new SubjectService()
    const universityService: UniversityService = new UniversityService()
    const userService: UserService = new UserService()

    const [subjects, setSubjects] = useState<ISubjectDTO[]>([])
    const [university, setUniversity] = useState<IUniversityDTO>()
    const [user, setUser] = useState<IUser>()


    useEffect(() => {
        //userService.getUserData()
        let userSelected: IUser | null = userService.getUserData();
        console.log(typeof (userSelected))

        if (userSelected) {

            universityService.getUniversityByHashId(userSelected.universityHashId)
                .then((university: IUniversityDTO) => setUniversity(university));

            subjectService.getSubjectsByCourse(userSelected.courseHashId)
                .then((subjects) => setSubjects(subjects))

            setUser(userSelected as IUser)
        }


    }, [])
    return (
        <GenericPageLayout title="AvaliAí">
            <div className={styles.container}>
                <div className={styles.pageFunctionsWrapper}>
                    <div className={styles.chipFilters}>
                        <h1>CHIP</h1>
                        <h1>CHIP</h1>
                        <h1>CHIP</h1>
                    </div>
                    <div className={styles.searchInputContainer}>

                        <div className={styles.inputItemWrapper}>
                            <div className={styles.inputSearchIconContainer}>
                                <ImSearch/>
                            </div>
                            <div className={styles.inputBox}>
                                <input type="text"
                                       placeholder="Pesquise por disciplinas"/>
                            </div>
                            <div className={styles.inputSearchImageContainer}>
                                <img
                                    src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.pixelstalk.net%2Fwp-content%2Fuploads%2F2016%2F04%2FKung-Fu-Panda-Wallpapers-HD.jpg&f=1&nofb=1&ipt=d74bdc8761099b4d75561600917d549a805a025f34cd90c60d040c728305fb16&ipo=images"
                                    alt=""/>
                            </div>
                        </div>

                    </div>
                </div>
                <div className={styles.subjectWrapper}>
                    <Masonry
                        columns={{xs: 1, sm: 2, md: 2, lg: 3, xl: 4}}
                        spacing={2}
                    >
                        {subjects.map((item, key) => (
                            <CardSubject
                                key={key} {...item}/>
                        ))}
                    </Masonry>
                </div>
            </div>
        </GenericPageLayout>
    )
}
