import { describe, it, expect, vi, beforeEach } from 'vitest';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import * as session from '../../utils/session';
import { Perfil } from './Perfil';

describe('Perfil', () => {
  beforeEach(() => {
    vi.restoreAllMocks();
    vi.spyOn(session, 'getToken').mockReturnValue('fake-token');
  });

  it('carga datos de perfil y guarda cambios', async () => {
    const fetchMock = vi.fn(async (url, options = {}) => {
      if (url.endsWith('/api/perfil') && (!options.method || options.method === 'GET')) {
        return { ok: true, json: async () => ({ username: 'cliente', email: 'cli@demo.com', nombre: 'Cliente Demo' }) };
      }
      if (url.endsWith('/api/perfil') && options.method === 'PUT') {
        return { ok: true, json: async () => ({ username: 'cliente', email: 'cli@demo.com', nombre: 'Cliente Editado' }) };
      }
      return { ok: true, json: async () => ({}) };
    });
    vi.spyOn(window, 'fetch').mockImplementation(fetchMock);

    render(
      <MemoryRouter>
        <Perfil />
      </MemoryRouter>
    );

    expect(await screen.findByDisplayValue(/cliente/i)).toBeInTheDocument();
    expect(screen.getByDisplayValue(/cli@demo.com/i)).toBeInTheDocument();
    expect(screen.getByDisplayValue(/Cliente Demo/i)).toBeInTheDocument();

    await userEvent.clear(screen.getByLabelText(/Nombre/i));
    await userEvent.type(screen.getByLabelText(/Nombre/i), 'Cliente Editado');
    await userEvent.click(screen.getByRole('button', { name: /guardar/i }));

    expect(await screen.findByText(/Perfil actualizado correctamente/i)).toBeInTheDocument();
  });
});

