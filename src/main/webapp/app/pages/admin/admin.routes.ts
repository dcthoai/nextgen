import {Routes} from '@angular/router';
import {AuthGuardFn} from '../../core/guards/auth.guard';
import {AUTH_ROUTES} from './auth/auth.routes';

export const ADMIN_ROUTES: Routes = [
  {
    path: 'home',
    title: 'Trang chủ',
    pathMatch: 'full',
    loadComponent: () => import('./dashboard/dashboard.component').then(m => m.DashboardComponent),
    canActivate: [AuthGuardFn]
  },
  {
    path: '',
    loadChildren: () => AUTH_ROUTES,
    canActivate: [AuthGuardFn]
  }
];
