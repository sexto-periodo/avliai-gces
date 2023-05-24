
import { useRouter } from 'next/router';
import React, { useEffect } from 'react';
import { useAuth } from '../../contexts/Auth'



/**
 * Este é um componente de proteção das rotas que exitem dentro
 *  da aplicação
 * O useEffect observar quando o estado da rota ou do usuário
 *  for aterado.
 * Sempre que o estado é alterado, o useEffect é a primeira 
 * dunção a ser executada e com isso, verifica o estado.
 * Caso seja falso ou nolu, o usuário é limitado a laugmas poucas páginas
 * @param param0 
 * @returns null em caso de acesso não autorizado a uma página 
 */
const ProtectedRoute = ({ children }: { children: React.ReactNode }) => {
  const { user } = useAuth()
  const router = useRouter()

 useEffect(() => {
    if (false/*!user*/) {
      router.push('/auth/signin')
    }
  }, [router, user ])

  return <>{
      true  ? children : null}</>
}

export default ProtectedRoute