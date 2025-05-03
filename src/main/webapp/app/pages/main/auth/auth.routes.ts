import {Routes} from '@angular/router';
import {AuthGuardFn} from '../../../core/guards/auth.guard';

export const AUTH_ROUTES: Routes = [
  {
    path: 'accounts',
    title: 'Quản lý tài khoản',
    pathMatch: 'full',
    loadComponent: () => import('./accounts/accounts.component').then(m => m.AccountsComponent),
    canActivate: [AuthGuardFn]
  },
  {
    path: 'roles',
    title: 'Quản lý vai trò',
    pathMatch: 'full',
    loadComponent: () => import('./roles/roles.component').then(m => m.RolesComponent),
    canActivate: [AuthGuardFn]
  },
];
