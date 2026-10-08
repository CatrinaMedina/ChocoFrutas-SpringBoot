import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { render, screen } from '@testing-library/react';
import { MemoryRouter, Routes, Route } from 'react-router-dom';
import * as session from '../utils/session';
import { ProtectedRoute } from './ProtectedRoute';

describe('ProtectedRoute', () => {
  const originalFetch = window.fetch;

  beforeEach(() => {
    vi.restoreAllMocks();
  });

  afterEach(() => {
    window.fetch = originalFetch;
  });

  it('redirige a /login cuando el token está expirado y el refresh falla', async () => {
    vi.spyOn(session, 'obtenerUsuario').mockReturnValue(null);
    vi.spyOn(session, 'getToken').mockReturnValue('expired-token');
    window.fetch = vi.fn().mockResolvedValue({ ok: false, json: async () => ({}) });

    render(
      <MemoryRouter initialEntries={["/privado"]}>
        <Routes>
          <Route path="/login" element={<h1>Login</h1>} />
          <Route
            path="/privado"
            element={
              <ProtectedRoute>
                <h1>Privado</h1>
              </ProtectedRoute>
            }
          />
        </Routes>
      </MemoryRouter>
    );

    expect(await screen.findByRole('heading', { name: /Login/i })).toBeInTheDocument();
  });

  it('redirige a / cuando el rol no coincide', async () => {
    vi.spyOn(session, 'obtenerUsuario').mockReturnValue({ username: 'cliente', rol: 'CLIENTE', token: 't' });
    vi.spyOn(session, 'getToken').mockReturnValue('t');

    render(
      <MemoryRouter initialEntries={["/admin"]}>
        <Routes>
          <Route path="/" element={<h1>Home</h1>} />
          <Route
            path="/admin"
            element={
              <ProtectedRoute role="ADMIN">
                <h1>Panel Admin</h1>
              </ProtectedRoute>
            }
          />
        </Routes>
      </MemoryRouter>
    );

    expect(await screen.findByRole('heading', { name: /Home/i })).toBeInTheDocument();
  });
});

