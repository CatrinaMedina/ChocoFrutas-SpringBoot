import { describe, it, expect, vi, beforeEach } from 'vitest';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import * as session from '../../utils/session';
import { ProcesoPago } from './ProcesoPago';

describe('ProcesoPago', () => {
  beforeEach(() => {
    vi.restoreAllMocks();
    localStorage.setItem('carritoParaPago', JSON.stringify({
      productos: [{ nombre: 'Producto', cantidad: 1, subtotal: 1000 }],
      total: 1000,
      fecha: new Date().toISOString()
    }));
    vi.spyOn(session, 'getToken').mockReturnValue('fake-token');
    vi.spyOn(window, 'fetch').mockResolvedValue({
      ok: true,
      json: async () => ({ numero: 'CF-2025-1234', total: 1000, detalles: [] })
    });
    vi.spyOn(window, 'alert').mockImplementation(() => {});
  });

  it('valida formulario y confirma pedido', async () => {
    render(
      <MemoryRouter>
        <ProcesoPago />
      </MemoryRouter>
    );

    await userEvent.type(screen.getByLabelText(/Nombre Completo/i), 'Cliente Demo');
    await userEvent.type(screen.getByLabelText(/Email/i), 'cliente@demo.com');
    await userEvent.type(screen.getByLabelText(/Teléfono/i), '99999999');
    await userEvent.type(screen.getByLabelText(/Dirección/i), 'Calle 123');

    const regionSelect = screen.getByLabelText(/Región/i);
    await userEvent.selectOptions(regionSelect, 'Metropolitana de Santiago');

    const comunaSelect = screen.getByLabelText(/Comuna/i);
    await userEvent.selectOptions(comunaSelect, 'Santiago');

    const metodoTransferencia = screen.getByLabelText(/Transferencia/i);
    await userEvent.click(metodoTransferencia);

    const confirmarBtn = screen.getByRole('button', { name: /Confirmar Pedido/i });
    await userEvent.click(confirmarBtn);

    expect(window.alert).toHaveBeenCalledWith(expect.stringMatching(/Compra confirmada/i));
  });
});

