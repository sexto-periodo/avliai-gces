import ProtectedRoute from '@/shared/components/protected-route/ProtectedRoute';
import {AuthContextProvider} from '@/shared/contexts/Auth';
import Layout from '@/shared/layout/Layout';
import type {AppProps} from 'next/app'
import {useRouter} from 'next/router';
import theme from '../shared/theme/theme';
import '@/styles/global.css'
import { createTheme, ThemeProvider, styled } from '@mui/material/styles';

const noAuthRequired = ['/auth/signin', '/auth/signup']


export default function App({Component, pageProps}: AppProps) {

    const router = useRouter()
    return (
        <ThemeProvider theme={theme}>
            <AuthContextProvider>
                {noAuthRequired.includes(router.pathname) ? (
                    <Component {...pageProps} />
                ) : (
                    <>
                        <ProtectedRoute>
                            <Layout>
                                <Component {...pageProps} />
                            </Layout>
                        </ProtectedRoute>
                    </>
                )}
            </AuthContextProvider>
        </ThemeProvider>
    )
}

