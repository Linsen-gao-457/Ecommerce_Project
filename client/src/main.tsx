import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

import './app/layout/index.css';
import { RouterProvider } from 'react-router-dom';
import { router } from './app/router/Routes.tsx';
import React from 'react';
import { createRoot } from 'react-dom/client'; // Changed import

const root = createRoot(document.getElementById('root')!);
root.render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>
);