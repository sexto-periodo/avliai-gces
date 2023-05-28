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
import {IUniversityDTO} from "@/shared/models/IUniversity";
import {UniversityService} from "@/shared/services/UniversityService";

export default function SignUn() {

    const handleSubmit = (event: any) => {
        event.preventDefault();
        console.log(`Email: ${email}, Password: ${password}`);
    };

    const handleUniversityChange = (event: SelectChangeEvent) => {
        setUniversity(event.target.value as string);
    };

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [university, setUniversity] = useState<string>()

    const universityService: UniversityService = new UniversityService();

    useEffect(() => {
        universityService.getUniversities().then((universities) => console.log(universities));
    }, [])

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
                                onChange={(event) => setEmail(event.target.value)}
                            />
                            <TextField
                                sx={{ml: 1, width: '19ch'}}
                                type="text"
                                id="outlined-basic"
                                label="Sobrenome"
                                variant="outlined"
                                onChange={(event) => setEmail(event.target.value)}
                            />
                        </div>

                        <Box sx={{minWidth: 120, mt: 2}}>

                            <FormControl fullWidth>
                                <InputLabel id="demo-simple-select-label">Instituição de
                                    Ensino</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={university}
                                    label="Instituição de Ensino"
                                    onChange={handleUniversityChange}
                                >
                                    <MenuItem value="543b45c583bfff6c30e44a751103a24f">PUC
                                        Minas</MenuItem>
                                    <MenuItem value="e6379fe087f9853f4c55a6bcb3f22093">AvaliAi
                                        University</MenuItem>
                                </Select>
                            </FormControl>
                        </Box>


                        <div>
                            <TextField
                                sx={{mr: 1, width: '19ch', mt: 2}}

                                type="text"
                                id="outlined-basic"
                                label="Registro acadêmico"
                                variant="outlined"
                                onChange={(event) => setEmail(event.target.value)}
                            />
                            <TextField
                                sx={{ml: 1, width: '19ch', mt: 2}}
                                type="text"
                                id="outlined-basic"
                                label="Curso"
                                variant="outlined"
                                onChange={(event) => setEmail(event.target.value)}
                            />
                        </div>
                        <TextField
                            sx={{width: '40ch', mt: 2}}
                            fullWidth
                            type="email"
                            id="outlined-basic"
                            label="e-mail"
                            variant="outlined"
                            onChange={(event) => setEmail(event.target.value)}
                        />
                        <TextField
                            sx={{width: '40ch', mt: 2}}
                            fullWidth
                            type="password"
                            id="outlined-basic"
                            label="senha"
                            variant="outlined"
                            onChange={(event) => setPassword(event.target.value)}
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
