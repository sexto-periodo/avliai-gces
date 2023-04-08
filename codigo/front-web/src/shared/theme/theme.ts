import { ThemeOptions } from '@mui/material/styles';
import { ThemeProvider, createTheme } from '@mui/material/styles';

const themeOptions: ThemeOptions = {
    palette: {
        mode: 'light',
        primary: {
            main: '#7457b1',
        },
        secondary: {
            main: '#c397ff',
        },
    },
};

const theme = createTheme(themeOptions);
export default theme;