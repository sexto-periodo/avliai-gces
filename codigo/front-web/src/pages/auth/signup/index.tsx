import React, {useEffect, useState} from 'react'
import styles from './style.module.scss'
import TextField from '@mui/material/TextField';
import {
    Box,
    FormControl,
    InputAdornment,
    InputLabel, MenuItem,
    OutlinedInput,
    Select, SelectChangeEvent
} from "@mui/material";
import Button from '@mui/material/Button';
import FormGroup from '@mui/material/FormGroup';
import {IUniversityDTO} from "@/shared/domain/University/IUniversity";
import {UniversityService} from "@/shared/domain/University/UniversityService";
import {ICourseDTO} from "@/shared/domain/Course/ICourseDTO";
import {CourseService} from "@/shared/domain/Course/CourseService";
import {UserService} from "@/shared/domain/User/UserService";
import {AuthService} from "@/shared/services/Auth/AuthService";
import {useRouter} from "next/router";
import {useAuth} from "@/shared/contexts/Auth";
import {UserAuth} from "@/shared/domain/User/User";


export interface ISignUpForm{
    firstname: string
    lastname: string
    email: string
    password:string
    academicRegister: string
    university: IUniversityDTO | null
    course: ICourseDTO | null
    role: string
}
export default function SignUp() {


    const [universities, setUniversities]= useState<IUniversityDTO[]>([]);
    const [courses, setCourses] = useState<ICourseDTO[]>([]);

    const [selectedUniversity, setSelectedUniversity]= useState<IUniversityDTO>({
        cnpj: "",
        hashId: "",
        id: 0,
        name: ""
    });
    const [selectedCourse, setSelectedCourse]= useState<ICourseDTO>({
        hashId: "",
        id: 0,
        name: "",
        overtime: 0,
        statusCurriculum: false
    });

    const [ signUpFormData, setSignUpFormData ] = useState<ISignUpForm>({
        academicRegister: "",
        email: "",
        lastname: "",
        firstname: "",
        password: "",
        course: null,
        university: null,
        role: "USER"
    })


    const router = useRouter()
    const { user, setUser, login } = useAuth();
    const universityService: UniversityService = new UniversityService();
    const courseService: CourseService = new CourseService();
    const userService: UserService = new UserService();
    const authService: AuthService = new AuthService();

    useEffect(() => {
        universityService.getUniversities().then((universities) => setUniversities(universities));
    }, [])

    useEffect(() => {
        courseService.getCoursesByUniversityHashId((selectedUniversity as IUniversityDTO).hashId).then((courses) => setCourses(courses));
    }, [selectedUniversity])

    const handleSubmit = async (event: any) => {
        event.preventDefault();
        console.log(`Email: ${signUpFormData.email}, Password: ${signUpFormData.password}`);

        let isEmailValid = await userService.validateEmail(signUpFormData.email);
        if (!isEmailValid) {
            console.log("EMAIL INVÀLIDO")
            return
        }

        try {
            await authService.register(signUpFormData);
            await login(signUpFormData.email, signUpFormData.password)
            router.push('/')
        } catch (err) {
            console.log(err)
        }

    };

    const handleUniversityChange = (event: SelectChangeEvent) => {
        let selectedUniversity = universities.find(u => u.hashId === event.target.value);
        setSignUpFormData({...signUpFormData, university: selectedUniversity as IUniversityDTO});
        setSelectedUniversity(selectedUniversity as IUniversityDTO);
    };
    const handleCourseChange = (event: SelectChangeEvent) => {
        let selectedCourse = courses.find(c => c.hashId === event.target.value);
        setSelectedCourse(selectedCourse as ICourseDTO);
    };

    return (
        <div className={styles.container}>
            <div className={styles.leftView}>
                <h1 className={styles.appTitle}>AvaliAí</h1>
                <div className={styles.centerForm}>
                    <h2>Cadastrar</h2>

                    <form
                        className={styles.form}
                        onSubmit={handleSubmit}
                    >
                        <div>

                            <TextField
                                sx={{mr: 1, width: '19ch'}}

                                type="text"
                                id="outlined-basic"
                                label="Nome"
                                variant="outlined"
                                onChange={(event) => setSignUpFormData({
                                    ...signUpFormData,
                                    firstname: event.target.value
                                })}
                            />
                            <TextField
                                sx={{ml: 1, width: '19ch'}}
                                type="text"
                                id="outlined-basic"
                                label="Sobrenome"
                                variant="outlined"
                                onChange={(event) => setSignUpFormData({
                                    ...signUpFormData,
                                    lastname: event.target.value
                                })}
                            />
                        </div>

                        <Box sx={{minWidth: 120, mt: 2}}>

                            <FormControl fullWidth>
                                <InputLabel id="demo-simple-select-label">Instituição de
                                    Ensino</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    defaultValue={""}
                                    value={selectedUniversity?.hashId}
                                    label="Instituição de Ensino"
                                    onChange={handleUniversityChange}
                                >
                                    {
                                        universities?.map((item, key) =>
                                            <MenuItem key={key} value={item.hashId}>{item.name}</MenuItem> )
                                    }
                                </Select>
                            </FormControl>
                        </Box>


                        <div>
                            <TextField
                                sx={{mr: 1, width: '19ch', mt: 2}}
                                fullWidth
                                type="text"
                                id="outlined-basic"
                                label="Registro acadêmico"
                                variant="outlined"
                                onChange={(event) => setSignUpFormData({
                                    ...signUpFormData,
                                    academicRegister: event.target.value
                                })}
                            />
                            <Box sx={{minWidth: 120, mt: 2}}>

                                <FormControl fullWidth>
                                    <InputLabel id="course-input-label">Curso</InputLabel>
                                    <Select
                                        labelId="course-input"
                                        id="course-input-label"
                                        defaultValue={""}
                                        value={selectedCourse?.hashId}
                                        label="Curso"
                                        onChange={handleCourseChange}
                                    >
                                        {
                                            courses?.map((item, key) =>
                                                <MenuItem key={key} value={item.hashId}>{item.name}</MenuItem> )
                                        }
                                    </Select>
                                </FormControl>
                            </Box>
                        </div>
                        <TextField
                            sx={{width: '40ch', mt: 2}}
                            fullWidth
                            type="email"
                            id="outlined-basic"
                            label="e-mail"
                            variant="outlined"
                            onChange={(event) => setSignUpFormData({
                                ...signUpFormData,
                                email: event.target.value
                            })}
                        />
                        <TextField
                            sx={{width: '40ch', mt: 2}}
                            fullWidth
                            type="password"
                            id="outlined-basic"
                            label="senha"
                            variant="outlined"
                            onChange={(event) => setSignUpFormData({
                                ...signUpFormData,
                                password: event.target.value
                            })}
                        />
                        <Button variant="contained"
                                disableElevation
                                type="submit"
                                sx={{ml: 1, mr: 1, mt: 3}}
                        >
                            Cadastrar
                        </Button>
                    </form>
                </div>
            </div>
            <div className={styles.rightView}>

            </div>
        </div>
    )
}
