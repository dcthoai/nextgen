-- Permission Tree Structure in the System
--      Manage System: view, update
--      Manage Accounts: view, create, update, delete
--      Manage Roles: view, create, update, delete
--      Manage Customers: view, update, delete
--      Manage Home: view, update
--      Manage About:
--          Stories: view, update
--          Partners: view, create, update, delete
--          Mottos: view, create, update, delete
--          Staffs: view, create, update, delete
--          Contact form: view, update
--      Manage Products: view, create, update, delete
--      Manage Works:
--          Project Categories: view, create, update, delete
--          Projects: view, create, update, delete
--      Manage Recruitment (jobs): view, create, update, delete
--      Manage Company infos: view, update

USE `nextgen_brand`;

-- Reset AUTO_INCREMENT to 1
ALTER TABLE `permission` AUTO_INCREMENT = 1;


-- Manage System
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.system', '01', 'permission.system.description', NULL, NULL, 'admin');

SET @system_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.system.view', '0101', 'permission.system.view.description', @system_id, '01', 'admin'),
('permission.system.update', '0102', 'permission.system.update.description', @system_id, '01', 'admin');

-- Manage Accounts
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.account', '02', 'permission.account.description', NULL, NULL, 'admin');

SET @account_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.account.view', '0201', 'permission.account.view.description', @account_id, '02', 'admin'),
('permission.account.create', '0202', 'permission.account.create.description', @account_id, '02', 'admin'),
('permission.account.update', '0203', 'permission.account.update.description', @account_id, '02', 'admin'),
('permission.account.delete', '0204', 'permission.account.delete.description', @account_id, '02', 'admin');

-- Manage Roles
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.role', '03', 'permission.role.description', NULL, NULL, 'admin');

SET @role_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.role.view', '0301', 'permission.role.view.description', @role_id, '03', 'admin'),
('permission.role.create', '0302', 'permission.role.create.description', @role_id, '03', 'admin'),
('permission.role.update', '0303', 'permission.role.update.description', @role_id, '03', 'admin'),
('permission.role.delete', '0304', 'permission.role.delete.description', @role_id, '03', 'admin');

-- Manage Customers
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.customer', '04', 'permission.customer.description', NULL, NULL, 'admin');

SET @customer_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.customer.view', '0401', 'permission.customer.view.description', @customer_id, '04', 'admin'),
('permission.customer.update', '0402', 'permission.customer.update.description', @customer_id, '04', 'admin'),
('permission.customer.delete', '0403', 'permission.customer.delete.description', @customer_id, '04', 'admin');

-- Manage Home
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.home', '05', 'permission.home.description', NULL, NULL, 'admin');

SET @home_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.home.view', '0501', 'permission.home.view.description', @home_id, '05', 'admin'),
('permission.home.update', '0502', 'permission.home.update.description', @home_id, '05', 'admin');

-- Manage About
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.about', '06', 'permission.about.description', NULL, NULL, 'admin');

SET @about_id = LAST_INSERT_ID();

-- Manage About: Stories
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.about.story', '0601', 'permission.about.story.description', @about_id, '06', 'admin');

SET @story_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.about.story.view', '060101', 'permission.about.story.view.description', @story_id, '0601', 'admin'),
('permission.about.story.update', '060102', 'permission.about.story.update.description', @story_id, '0601', 'admin');

-- Manage About: Partners
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.about.partner', '0602', 'permission.about.partner.description', @about_id, '06', 'admin');

SET @partner_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.about.partner.view', '060201', 'permission.about.partner.view.description', @partner_id, '0602', 'admin'),
('permission.about.partner.create', '060202', 'permission.about.partner.create.description', @partner_id, '0602', 'admin'),
('permission.about.partner.update', '060203', 'permission.about.partner.update.description', @partner_id, '0602', 'admin'),
('permission.about.partner.delete', '060204', 'permission.about.partner.delete.description', @partner_id, '0602', 'admin');

-- Manage About: Mottos
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.about.motto', '0603', 'permission.about.motto.description', @about_id, '06', 'admin');

SET @motto_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.about.motto.view', '060301', 'permission.about.motto.view.description', @motto_id, '0603', 'admin'),
('permission.about.motto.create', '060302', 'permission.about.motto.create.description', @motto_id, '0603', 'admin'),
('permission.about.motto.update', '060303', 'permission.about.motto.update.description', @motto_id, '0603', 'admin'),
('permission.about.motto.delete', '060304', 'permission.about.motto.delete.description', @motto_id, '0603', 'admin');

-- Manage About: Staffs
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.about.staff', '0604', 'permission.about.staff.description', @about_id, '06', 'admin');

SET @staff_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.about.staff.view', '060401', 'permission.about.staff.view.description', @staff_id, '0604', 'admin'),
('permission.about.staff.create', '060402', 'permission.about.staff.create.description', @staff_id, '0604', 'admin'),
('permission.about.staff.update', '060403', 'permission.about.staff.update.description', @staff_id, '0604', 'admin'),
('permission.about.staff.delete', '060404', 'permission.about.staff.delete.description', @staff_id, '0604', 'admin');

-- Manage About: Contact form
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.about.contactForm', '0605', 'permission.about.contactForm.description', @about_id, '06', 'admin');

SET @contact_form_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.about.contactForm.view', '060501', 'permission.about.contactForm.view.description', @contact_form_id, '0605', 'admin'),
('permission.about.contactForm.update', '060502', 'permission.about.contactForm.update.description', @contact_form_id, '0605', 'admin');

-- Manage Products
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.product', '07', 'permission.product.description', NULL, NULL, 'admin');

SET @product_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.product.view', '0701', 'permission.product.view.description', @product_id, '07', 'admin'),
('permission.product.create', '0702', 'permission.product.create.description', @product_id, '07', 'admin'),
('permission.product.update', '0703', 'permission.product.update.description', @product_id, '07', 'admin'),
('permission.product.delete', '0704', 'permission.product.delete.description', @product_id, '07', 'admin');

-- Manage Works
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.works', '08', 'permission.works.description', NULL, NULL, 'admin');

SET @works_id = LAST_INSERT_ID();

-- Manage Works: Categories
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.works.category', '0801', 'permission.works.category.description', @works_id, '08', 'admin');

SET @work_category_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.works.category.view', '080101', 'permission.works.category.view.description', @work_category_id, '0801', 'admin'),
('permission.works.category.create', '080102', 'permission.works.category.create.description', @work_category_id, '0801', 'admin'),
('permission.works.category.update', '080103', 'permission.works.category.update.description', @work_category_id, '0801', 'admin'),
('permission.works.category.delete', '080104', 'permission.works.category.delete.description', @work_category_id, '0801', 'admin');

-- Manage Works: Projects
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.works.project', '0802', 'permission.works.project.description', @works_id, '08', 'admin');

SET @project_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.works.project.view', '080201', 'permission.works.project.view.description', @project_id, '0802', 'admin'),
('permission.works.project.create', '080202', 'permission.works.project.create.description', @project_id, '0802', 'admin'),
('permission.works.project.update', '080203', 'permission.works.project.update.description', @project_id, '0802', 'admin'),
('permission.works.project.delete', '080204', 'permission.works.project.delete.description', @project_id, '0802', 'admin');

-- Manage Recruitment: Jobs
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.jobs', '09', 'permission.jobs.description', NULL, NULL, 'admin');

SET @jobs_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.jobs.view', '0901', 'permission.jobs.view.description', @jobs_id, '09', 'admin'),
('permission.jobs.create', '0902', 'permission.jobs.create.description', @jobs_id, '09', 'admin'),
('permission.jobs.update', '0903', 'permission.jobs.update.description', @jobs_id, '09', 'admin'),
('permission.jobs.delete', '0904', 'permission.jobs.delete.description', @jobs_id, '09', 'admin');

-- Manage Company info
INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`)
VALUES ('permission.company', '10', 'permission.company.description', NULL, NULL, 'admin');

SET @company_info_id = LAST_INSERT_ID();

INSERT INTO `permission` (`name`, `code`, `description`, `parent_id`, `parent_code`, `created_by`) VALUES
('permission.company.view', '1001', 'permission.company.view.description', @company_info_id, '10', 'admin'),
('permission.company.update', '1002', 'permission.company.update.description', @company_info_id, '10', 'admin');
