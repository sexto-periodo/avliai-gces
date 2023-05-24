import React, {useState} from 'react'
import styles from './style.module.scss'
import TextField from '@mui/material/TextField';
import {FormControl, InputAdornment, InputLabel, OutlinedInput} from "@mui/material";
import Button from '@mui/material/Button';
import FormGroup from '@mui/material/FormGroup';
import {useRouter} from "next/router";
import {useAuth} from "@/shared/contexts/Auth";

export default function SignIn() {

    const router = useRouter()
    const { user, login } = useAuth()

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    /**
     * Essa função detecta a submissão do formulário e chama a função
     * de login do contexto para que o usuário seja altenticado e
     * para que o contexto mude de maneira asincrona
     * @param HTMLFormElement
     */
    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        try {
            await login(email, password)
            router.push('/')
        } catch (err) {
            console.log(err)
        }
    };


    return (
        <div className={styles.container}>
            <div className={styles.leftView}>
                <h1 className={styles.appTitle}>AvaliAí</h1>
                <div className={styles.centerForm}>
                    <h2>Bem vindo de volta</h2>

                    <form
                        className={styles.form}
                        onSubmit={handleSubmit}
                    >
                        <TextField
                            sx={{ m: 1, width: '40ch', mt: 3 }}
                            type="email"
                            id="outlined-basic"
                            label="e-mail"
                            variant="outlined"
                            onChange={(event) => setEmail(event.target.value)}
                        />
                        <TextField
                            sx={{ m: 1, width: '40ch' }}
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
                            Entrar
                        </Button>
                    </form>
                </div>
            </div>
            <div className={styles.rightView}>

            </div>
        </div>
    )
}
