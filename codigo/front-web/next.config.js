/** @type {import('next').NextConfig} */
const nextConfig = {
  trailingSlash:true,
  reactStrictMode: true,
  disableStaticImages: true,
  images: {
    unoptimized: true,
    remotePatterns: [
      {
        protocol: 'https',
        hostname: '**',
        port: '',
        pathname: '**',
      },
    ],
  }
}

module.exports = nextConfig
