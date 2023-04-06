export interface IUser {
    nome: string,
    email: string,
    acesso: string,
    senha: string
}

export interface UserAuth {
    email: string,
    token: string,
}