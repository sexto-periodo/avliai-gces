import ProtectedRoute from '@/shared/components/protected-route/ProtectedRoute';
import { AuthContextProvider } from '@/shared/contexts/Auth';
import { AuthService } from '@/shared/services/AuthService';
import '@/styles/globals.css'
import type { AppProps } from 'next/app'
import { useRouter } from 'next/router';

const noAuthRequired = ['/auth/signin', '/auth/signup']

export default function App({ Component, pageProps }: AppProps) {
  const authService = new AuthService();
  const router = useRouter()
  return (

    <AuthContextProvider>

        {noAuthRequired.includes(router.pathname) ? (
          <Component {...pageProps} />
        ) : (
          <>
            <ProtectedRoute>
              {/* <Layout>
                <Component {...pageProps} />
              </Layout> */}
              <Component {...pageProps} />
            </ProtectedRoute>
          </>
        )}
      </AuthContextProvider>
  )
}

