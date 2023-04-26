import React, {useState} from 'react'
import styles from './style.module.scss'
import TextField from '@mui/material/TextField';
import {FormControl, InputAdornment, InputLabel, OutlinedInput} from "@mui/material";
import Button from '@mui/material/Button';
import FormGroup from '@mui/material/FormGroup';

export default function SignUn() {

    const handleSubmit = (event: any) => {
        event.preventDefault();
        console.log(`Email: ${email}, Password: ${password}`);
    };

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

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
                            sx={{ mr:1, width: '19ch'}}

                            type="text"
                            id="outlined-basic"
                            label="Nome"
                            variant="outlined"
                            onChange={(event) => setEmail(event.target.value)}
                        />
                        <TextField
                            sx={{ ml: 1, width: '19ch'}}
                            type="text"
                            id="outlined-basic"
                            label="Sobrenome"
                            variant="outlined"
                            onChange={(event) => setEmail(event.target.value)}
                        />
                        </div>

                        <TextField
                            sx={{ width: '40ch', mt: 2 }}
                            fullWidth
                            type="email"
                            id="outlined-basic"
                            label="Instituição de ensino"
                            variant="outlined"
                            onChange={(event) => setEmail(event.target.value)}
                        />
                        <div>
                            <TextField
                                sx={{ mr:1, width: '19ch', mt: 2}}

                                type="text"
                                id="outlined-basic"
                                label="Registro acadêmico"
                                variant="outlined"
                                onChange={(event) => setEmail(event.target.value)}
                            />
                            <TextField
                                sx={{ ml: 1, width: '19ch', mt: 2}}
                                type="text"
                                id="outlined-basic"
                                label="Curso"
                                variant="outlined"
                                onChange={(event) => setEmail(event.target.value)}
                            />
                        </div>
                        <TextField
                            sx={{ width: '40ch', mt: 2 }}
                            fullWidth
                            type="email"
                            id="outlined-basic"
                            label="e-mail"
                            variant="outlined"
                            onChange={(event) => setEmail(event.target.value)}
                        />
                        <TextField
                            sx={{ width: '40ch', mt:2 }}
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
                                sx={{ml:1, mr: 1, mt: 3}}
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
