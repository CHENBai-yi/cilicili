/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : cili-quasar-admin

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 24/01/2024 21:05:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for database_connection
-- ----------------------------
DROP TABLE IF EXISTS `database_connection`;
CREATE TABLE `database_connection`
(
    `id`       int                                                     NOT NULL AUTO_INCREMENT,
    `url`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `driver`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `scheme`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of database_connection
-- ----------------------------

-- ----------------------------
-- Table structure for plugin_achievement_category
-- ----------------------------
DROP TABLE IF EXISTS `plugin_achievement_category`;
CREATE TABLE `plugin_achievement_category`
(
    `id`         bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at` datetime                                                      NULL DEFAULT NULL,
    `created_by` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at` datetime                                                      NULL DEFAULT NULL,
    `updated_by` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at` datetime                                                      NULL DEFAULT NULL,
    `sort`       bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `category`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成就编码',
    `code`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '小编码',
    `name`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成就名称',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_plugin_achievement_category_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_plugin_achievement_category_category` (`category` ASC) USING BTREE,
    INDEX `idx_plugin_achievement_category_code` (`code` ASC) USING BTREE,
    INDEX `idx_plugin_achievement_category_created_by` (`created_by` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plugin_achievement_category
-- ----------------------------
INSERT INTO `plugin_achievement_category`
VALUES (1, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1201, 'yesNo_yes', 'onOff_on',
        '发现某个可以旋转的图标', 'QiYu', 'QiYu-Roll-Icon', '摇滚吧，少年！');
INSERT INTO `plugin_achievement_category`
VALUES (2, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1202, 'yesNo_yes', 'onOff_on',
        '发现404页面', 'QiYu', 'QiYu-Find-404', '未知领域！');

-- ----------------------------
-- Table structure for plugin_achievement_obtain
-- ----------------------------
DROP TABLE IF EXISTS `plugin_achievement_obtain`;
CREATE TABLE `plugin_achievement_obtain`
(
    `category_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成就编码',
    `created_by`    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '获得人',
    `created_at`    datetime                                                      NOT NULL COMMENT '获取时间',
    INDEX `idx_plugin_achievement_obtain_category_code` (`category_code` ASC) USING BTREE,
    INDEX `idx_plugin_achievement_obtain_created_by` (`created_by` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plugin_achievement_obtain
-- ----------------------------
INSERT INTO `plugin_achievement_obtain`
VALUES ('QiYu-Roll-Code', 'admin', '2023-12-04 13:14:42');

-- ----------------------------
-- Table structure for plugin_example_test_data
-- ----------------------------
DROP TABLE IF EXISTS `plugin_example_test_data`;
CREATE TABLE `plugin_example_test_data`
(
    `id`         bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at` datetime                                                      NULL DEFAULT NULL,
    `created_by` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at` datetime                                                      NULL DEFAULT NULL,
    `updated_by` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at` datetime                                                      NULL DEFAULT NULL,
    `sort`       bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `column1`    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第1列',
    `column2`    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第2列',
    `column3`    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第3列',
    `column4`    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第4列',
    `column5`    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第5列',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_plugin_example_test_data_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_plugin_example_test_data_deleted_at` (`deleted_at` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 500
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plugin_example_test_data
-- ----------------------------
INSERT INTO `plugin_example_test_data`
VALUES (1, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 101, 'yesNo_no', 'onOff_on', '', '第1列内容1',
        '第2列内容1', '第3列内容1', '第4列内容1', '第5列内容1');
INSERT INTO `plugin_example_test_data`
VALUES (2, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 102, 'yesNo_no', 'onOff_on', '', '第1列内容2',
        '第2列内容2', '第3列内容2', '第4列内容2', '第5列内容2');
INSERT INTO `plugin_example_test_data`
VALUES (3, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 103, 'yesNo_no', 'onOff_on', '', '第1列内容3',
        '第2列内容3', '第3列内容3', '第4列内容3', '第5列内容3');
INSERT INTO `plugin_example_test_data`
VALUES (4, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 104, 'yesNo_no', 'onOff_on', '', '第1列内容4',
        '第2列内容4', '第3列内容4', '第4列内容4', '第5列内容4');
INSERT INTO `plugin_example_test_data`
VALUES (5, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 105, 'yesNo_no', 'onOff_on', '', '第1列内容5',
        '第2列内容5', '第3列内容5', '第4列内容5', '第5列内容5');
INSERT INTO `plugin_example_test_data`
VALUES (6, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 106, 'yesNo_no', 'onOff_on', '', '第1列内容6',
        '第2列内容6', '第3列内容6', '第4列内容6', '第5列内容6');
INSERT INTO `plugin_example_test_data`
VALUES (7, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 107, 'yesNo_no', 'onOff_on', '', '第1列内容7',
        '第2列内容7', '第3列内容7', '第4列内容7', '第5列内容7');
INSERT INTO `plugin_example_test_data`
VALUES (8, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 108, 'yesNo_no', 'onOff_on', '', '第1列内容8',
        '第2列内容8', '第3列内容8', '第4列内容8', '第5列内容8');
INSERT INTO `plugin_example_test_data`
VALUES (9, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 109, 'yesNo_no', 'onOff_on', '', '第1列内容9',
        '第2列内容9', '第3列内容9', '第4列内容9', '第5列内容9');
INSERT INTO `plugin_example_test_data`
VALUES (10, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 110, 'yesNo_no', 'onOff_on', '', '第1列内容10',
        '第2列内容10', '第3列内容10', '第4列内容10', '第5列内容10');
INSERT INTO `plugin_example_test_data`
VALUES (11, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 111, 'yesNo_no', 'onOff_on', '', '第1列内容11',
        '第2列内容11', '第3列内容11', '第4列内容11', '第5列内容11');
INSERT INTO `plugin_example_test_data`
VALUES (12, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 112, 'yesNo_no', 'onOff_on', '', '第1列内容12',
        '第2列内容12', '第3列内容12', '第4列内容12', '第5列内容12');
INSERT INTO `plugin_example_test_data`
VALUES (13, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 113, 'yesNo_no', 'onOff_on', '', '第1列内容13',
        '第2列内容13', '第3列内容13', '第4列内容13', '第5列内容13');
INSERT INTO `plugin_example_test_data`
VALUES (14, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 114, 'yesNo_no', 'onOff_on', '', '第1列内容14',
        '第2列内容14', '第3列内容14', '第4列内容14', '第5列内容14');
INSERT INTO `plugin_example_test_data`
VALUES (15, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 115, 'yesNo_no', 'onOff_on', '', '第1列内容15',
        '第2列内容15', '第3列内容15', '第4列内容15', '第5列内容15');
INSERT INTO `plugin_example_test_data`
VALUES (16, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 116, 'yesNo_no', 'onOff_on', '', '第1列内容16',
        '第2列内容16', '第3列内容16', '第4列内容16', '第5列内容16');
INSERT INTO `plugin_example_test_data`
VALUES (17, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 117, 'yesNo_no', 'onOff_on', '', '第1列内容17',
        '第2列内容17', '第3列内容17', '第4列内容17', '第5列内容17');
INSERT INTO `plugin_example_test_data`
VALUES (18, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 118, 'yesNo_no', 'onOff_on', '', '第1列内容18',
        '第2列内容18', '第3列内容18', '第4列内容18', '第5列内容18');
INSERT INTO `plugin_example_test_data`
VALUES (19, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 119, 'yesNo_no', 'onOff_on', '', '第1列内容19',
        '第2列内容19', '第3列内容19', '第4列内容19', '第5列内容19');
INSERT INTO `plugin_example_test_data`
VALUES (20, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 120, 'yesNo_no', 'onOff_on', '', '第1列内容20',
        '第2列内容20', '第3列内容20', '第4列内容20', '第5列内容20');
INSERT INTO `plugin_example_test_data`
VALUES (21, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 121, 'yesNo_no', 'onOff_on', '', '第1列内容21',
        '第2列内容21', '第3列内容21', '第4列内容21', '第5列内容21');
INSERT INTO `plugin_example_test_data`
VALUES (22, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 122, 'yesNo_no', 'onOff_on', '', '第1列内容22',
        '第2列内容22', '第3列内容22', '第4列内容22', '第5列内容22');
INSERT INTO `plugin_example_test_data`
VALUES (23, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 123, 'yesNo_no', 'onOff_on', '', '第1列内容23',
        '第2列内容23', '第3列内容23', '第4列内容23', '第5列内容23');
INSERT INTO `plugin_example_test_data`
VALUES (24, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 124, 'yesNo_no', 'onOff_on', '', '第1列内容24',
        '第2列内容24', '第3列内容24', '第4列内容24', '第5列内容24');
INSERT INTO `plugin_example_test_data`
VALUES (25, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 125, 'yesNo_no', 'onOff_on', '', '第1列内容25',
        '第2列内容25', '第3列内容25', '第4列内容25', '第5列内容25');
INSERT INTO `plugin_example_test_data`
VALUES (26, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 126, 'yesNo_no', 'onOff_on', '', '第1列内容26',
        '第2列内容26', '第3列内容26', '第4列内容26', '第5列内容26');
INSERT INTO `plugin_example_test_data`
VALUES (27, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 127, 'yesNo_no', 'onOff_on', '', '第1列内容27',
        '第2列内容27', '第3列内容27', '第4列内容27', '第5列内容27');
INSERT INTO `plugin_example_test_data`
VALUES (28, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 128, 'yesNo_no', 'onOff_on', '', '第1列内容28',
        '第2列内容28', '第3列内容28', '第4列内容28', '第5列内容28');
INSERT INTO `plugin_example_test_data`
VALUES (29, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 129, 'yesNo_no', 'onOff_on', '', '第1列内容29',
        '第2列内容29', '第3列内容29', '第4列内容29', '第5列内容29');
INSERT INTO `plugin_example_test_data`
VALUES (30, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 130, 'yesNo_no', 'onOff_on', '', '第1列内容30',
        '第2列内容30', '第3列内容30', '第4列内容30', '第5列内容30');
INSERT INTO `plugin_example_test_data`
VALUES (31, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 131, 'yesNo_no', 'onOff_on', '', '第1列内容31',
        '第2列内容31', '第3列内容31', '第4列内容31', '第5列内容31');
INSERT INTO `plugin_example_test_data`
VALUES (32, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 132, 'yesNo_no', 'onOff_on', '', '第1列内容32',
        '第2列内容32', '第3列内容32', '第4列内容32', '第5列内容32');
INSERT INTO `plugin_example_test_data`
VALUES (33, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 133, 'yesNo_no', 'onOff_on', '', '第1列内容33',
        '第2列内容33', '第3列内容33', '第4列内容33', '第5列内容33');
INSERT INTO `plugin_example_test_data`
VALUES (34, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 134, 'yesNo_no', 'onOff_on', '', '第1列内容34',
        '第2列内容34', '第3列内容34', '第4列内容34', '第5列内容34');
INSERT INTO `plugin_example_test_data`
VALUES (35, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 135, 'yesNo_no', 'onOff_on', '', '第1列内容35',
        '第2列内容35', '第3列内容35', '第4列内容35', '第5列内容35');
INSERT INTO `plugin_example_test_data`
VALUES (36, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 136, 'yesNo_no', 'onOff_on', '', '第1列内容36',
        '第2列内容36', '第3列内容36', '第4列内容36', '第5列内容36');
INSERT INTO `plugin_example_test_data`
VALUES (37, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 137, 'yesNo_no', 'onOff_on', '', '第1列内容37',
        '第2列内容37', '第3列内容37', '第4列内容37', '第5列内容37');
INSERT INTO `plugin_example_test_data`
VALUES (38, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 138, 'yesNo_no', 'onOff_on', '', '第1列内容38',
        '第2列内容38', '第3列内容38', '第4列内容38', '第5列内容38');
INSERT INTO `plugin_example_test_data`
VALUES (39, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 139, 'yesNo_no', 'onOff_on', '', '第1列内容39',
        '第2列内容39', '第3列内容39', '第4列内容39', '第5列内容39');
INSERT INTO `plugin_example_test_data`
VALUES (40, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 140, 'yesNo_no', 'onOff_on', '', '第1列内容40',
        '第2列内容40', '第3列内容40', '第4列内容40', '第5列内容40');
INSERT INTO `plugin_example_test_data`
VALUES (41, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 141, 'yesNo_no', 'onOff_on', '', '第1列内容41',
        '第2列内容41', '第3列内容41', '第4列内容41', '第5列内容41');
INSERT INTO `plugin_example_test_data`
VALUES (42, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 142, 'yesNo_no', 'onOff_on', '', '第1列内容42',
        '第2列内容42', '第3列内容42', '第4列内容42', '第5列内容42');
INSERT INTO `plugin_example_test_data`
VALUES (43, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 143, 'yesNo_no', 'onOff_on', '', '第1列内容43',
        '第2列内容43', '第3列内容43', '第4列内容43', '第5列内容43');
INSERT INTO `plugin_example_test_data`
VALUES (44, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 144, 'yesNo_no', 'onOff_on', '', '第1列内容44',
        '第2列内容44', '第3列内容44', '第4列内容44', '第5列内容44');
INSERT INTO `plugin_example_test_data`
VALUES (45, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 145, 'yesNo_no', 'onOff_on', '', '第1列内容45',
        '第2列内容45', '第3列内容45', '第4列内容45', '第5列内容45');
INSERT INTO `plugin_example_test_data`
VALUES (46, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 146, 'yesNo_no', 'onOff_on', '', '第1列内容46',
        '第2列内容46', '第3列内容46', '第4列内容46', '第5列内容46');
INSERT INTO `plugin_example_test_data`
VALUES (47, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 147, 'yesNo_no', 'onOff_on', '', '第1列内容47',
        '第2列内容47', '第3列内容47', '第4列内容47', '第5列内容47');
INSERT INTO `plugin_example_test_data`
VALUES (48, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 148, 'yesNo_no', 'onOff_on', '', '第1列内容48',
        '第2列内容48', '第3列内容48', '第4列内容48', '第5列内容48');
INSERT INTO `plugin_example_test_data`
VALUES (49, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 149, 'yesNo_no', 'onOff_on', '', '第1列内容49',
        '第2列内容49', '第3列内容49', '第4列内容49', '第5列内容49');
INSERT INTO `plugin_example_test_data`
VALUES (50, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 150, 'yesNo_no', 'onOff_on', '', '第1列内容50',
        '第2列内容50', '第3列内容50', '第4列内容50', '第5列内容50');
INSERT INTO `plugin_example_test_data`
VALUES (51, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 151, 'yesNo_no', 'onOff_on', '', '第1列内容51',
        '第2列内容51', '第3列内容51', '第4列内容51', '第5列内容51');
INSERT INTO `plugin_example_test_data`
VALUES (52, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 152, 'yesNo_no', 'onOff_on', '', '第1列内容52',
        '第2列内容52', '第3列内容52', '第4列内容52', '第5列内容52');
INSERT INTO `plugin_example_test_data`
VALUES (53, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 153, 'yesNo_no', 'onOff_on', '', '第1列内容53',
        '第2列内容53', '第3列内容53', '第4列内容53', '第5列内容53');
INSERT INTO `plugin_example_test_data`
VALUES (54, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 154, 'yesNo_no', 'onOff_on', '', '第1列内容54',
        '第2列内容54', '第3列内容54', '第4列内容54', '第5列内容54');
INSERT INTO `plugin_example_test_data`
VALUES (55, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 155, 'yesNo_no', 'onOff_on', '', '第1列内容55',
        '第2列内容55', '第3列内容55', '第4列内容55', '第5列内容55');
INSERT INTO `plugin_example_test_data`
VALUES (56, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 156, 'yesNo_no', 'onOff_on', '', '第1列内容56',
        '第2列内容56', '第3列内容56', '第4列内容56', '第5列内容56');
INSERT INTO `plugin_example_test_data`
VALUES (57, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 157, 'yesNo_no', 'onOff_on', '', '第1列内容57',
        '第2列内容57', '第3列内容57', '第4列内容57', '第5列内容57');
INSERT INTO `plugin_example_test_data`
VALUES (58, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 158, 'yesNo_no', 'onOff_on', '', '第1列内容58',
        '第2列内容58', '第3列内容58', '第4列内容58', '第5列内容58');
INSERT INTO `plugin_example_test_data`
VALUES (59, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 159, 'yesNo_no', 'onOff_on', '', '第1列内容59',
        '第2列内容59', '第3列内容59', '第4列内容59', '第5列内容59');
INSERT INTO `plugin_example_test_data`
VALUES (60, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 160, 'yesNo_no', 'onOff_on', '', '第1列内容60',
        '第2列内容60', '第3列内容60', '第4列内容60', '第5列内容60');
INSERT INTO `plugin_example_test_data`
VALUES (61, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 161, 'yesNo_no', 'onOff_on', '', '第1列内容61',
        '第2列内容61', '第3列内容61', '第4列内容61', '第5列内容61');
INSERT INTO `plugin_example_test_data`
VALUES (62, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 162, 'yesNo_no', 'onOff_on', '', '第1列内容62',
        '第2列内容62', '第3列内容62', '第4列内容62', '第5列内容62');
INSERT INTO `plugin_example_test_data`
VALUES (63, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 163, 'yesNo_no', 'onOff_on', '', '第1列内容63',
        '第2列内容63', '第3列内容63', '第4列内容63', '第5列内容63');
INSERT INTO `plugin_example_test_data`
VALUES (64, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 164, 'yesNo_no', 'onOff_on', '', '第1列内容64',
        '第2列内容64', '第3列内容64', '第4列内容64', '第5列内容64');
INSERT INTO `plugin_example_test_data`
VALUES (65, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 165, 'yesNo_no', 'onOff_on', '', '第1列内容65',
        '第2列内容65', '第3列内容65', '第4列内容65', '第5列内容65');
INSERT INTO `plugin_example_test_data`
VALUES (66, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 166, 'yesNo_no', 'onOff_on', '', '第1列内容66',
        '第2列内容66', '第3列内容66', '第4列内容66', '第5列内容66');
INSERT INTO `plugin_example_test_data`
VALUES (67, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 167, 'yesNo_no', 'onOff_on', '', '第1列内容67',
        '第2列内容67', '第3列内容67', '第4列内容67', '第5列内容67');
INSERT INTO `plugin_example_test_data`
VALUES (68, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 168, 'yesNo_no', 'onOff_on', '', '第1列内容68',
        '第2列内容68', '第3列内容68', '第4列内容68', '第5列内容68');
INSERT INTO `plugin_example_test_data`
VALUES (69, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 169, 'yesNo_no', 'onOff_on', '', '第1列内容69',
        '第2列内容69', '第3列内容69', '第4列内容69', '第5列内容69');
INSERT INTO `plugin_example_test_data`
VALUES (70, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 170, 'yesNo_no', 'onOff_on', '', '第1列内容70',
        '第2列内容70', '第3列内容70', '第4列内容70', '第5列内容70');
INSERT INTO `plugin_example_test_data`
VALUES (71, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 171, 'yesNo_no', 'onOff_on', '', '第1列内容71',
        '第2列内容71', '第3列内容71', '第4列内容71', '第5列内容71');
INSERT INTO `plugin_example_test_data`
VALUES (72, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 172, 'yesNo_no', 'onOff_on', '', '第1列内容72',
        '第2列内容72', '第3列内容72', '第4列内容72', '第5列内容72');
INSERT INTO `plugin_example_test_data`
VALUES (73, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 173, 'yesNo_no', 'onOff_on', '', '第1列内容73',
        '第2列内容73', '第3列内容73', '第4列内容73', '第5列内容73');
INSERT INTO `plugin_example_test_data`
VALUES (74, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 174, 'yesNo_no', 'onOff_on', '', '第1列内容74',
        '第2列内容74', '第3列内容74', '第4列内容74', '第5列内容74');
INSERT INTO `plugin_example_test_data`
VALUES (75, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 175, 'yesNo_no', 'onOff_on', '', '第1列内容75',
        '第2列内容75', '第3列内容75', '第4列内容75', '第5列内容75');
INSERT INTO `plugin_example_test_data`
VALUES (76, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 176, 'yesNo_no', 'onOff_on', '', '第1列内容76',
        '第2列内容76', '第3列内容76', '第4列内容76', '第5列内容76');
INSERT INTO `plugin_example_test_data`
VALUES (77, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 177, 'yesNo_no', 'onOff_on', '', '第1列内容77',
        '第2列内容77', '第3列内容77', '第4列内容77', '第5列内容77');
INSERT INTO `plugin_example_test_data`
VALUES (78, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 178, 'yesNo_no', 'onOff_on', '', '第1列内容78',
        '第2列内容78', '第3列内容78', '第4列内容78', '第5列内容78');
INSERT INTO `plugin_example_test_data`
VALUES (79, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 179, 'yesNo_no', 'onOff_on', '', '第1列内容79',
        '第2列内容79', '第3列内容79', '第4列内容79', '第5列内容79');
INSERT INTO `plugin_example_test_data`
VALUES (80, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 180, 'yesNo_no', 'onOff_on', '', '第1列内容80',
        '第2列内容80', '第3列内容80', '第4列内容80', '第5列内容80');
INSERT INTO `plugin_example_test_data`
VALUES (81, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 181, 'yesNo_no', 'onOff_on', '', '第1列内容81',
        '第2列内容81', '第3列内容81', '第4列内容81', '第5列内容81');
INSERT INTO `plugin_example_test_data`
VALUES (82, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 182, 'yesNo_no', 'onOff_on', '', '第1列内容82',
        '第2列内容82', '第3列内容82', '第4列内容82', '第5列内容82');
INSERT INTO `plugin_example_test_data`
VALUES (83, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 183, 'yesNo_no', 'onOff_on', '', '第1列内容83',
        '第2列内容83', '第3列内容83', '第4列内容83', '第5列内容83');
INSERT INTO `plugin_example_test_data`
VALUES (84, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 184, 'yesNo_no', 'onOff_on', '', '第1列内容84',
        '第2列内容84', '第3列内容84', '第4列内容84', '第5列内容84');
INSERT INTO `plugin_example_test_data`
VALUES (85, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 185, 'yesNo_no', 'onOff_on', '', '第1列内容85',
        '第2列内容85', '第3列内容85', '第4列内容85', '第5列内容85');
INSERT INTO `plugin_example_test_data`
VALUES (86, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 186, 'yesNo_no', 'onOff_on', '', '第1列内容86',
        '第2列内容86', '第3列内容86', '第4列内容86', '第5列内容86');
INSERT INTO `plugin_example_test_data`
VALUES (87, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 187, 'yesNo_no', 'onOff_on', '', '第1列内容87',
        '第2列内容87', '第3列内容87', '第4列内容87', '第5列内容87');
INSERT INTO `plugin_example_test_data`
VALUES (88, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 188, 'yesNo_no', 'onOff_on', '', '第1列内容88',
        '第2列内容88', '第3列内容88', '第4列内容88', '第5列内容88');
INSERT INTO `plugin_example_test_data`
VALUES (89, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 189, 'yesNo_no', 'onOff_on', '', '第1列内容89',
        '第2列内容89', '第3列内容89', '第4列内容89', '第5列内容89');
INSERT INTO `plugin_example_test_data`
VALUES (90, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 190, 'yesNo_no', 'onOff_on', '', '第1列内容90',
        '第2列内容90', '第3列内容90', '第4列内容90', '第5列内容90');
INSERT INTO `plugin_example_test_data`
VALUES (91, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 191, 'yesNo_no', 'onOff_on', '', '第1列内容91',
        '第2列内容91', '第3列内容91', '第4列内容91', '第5列内容91');
INSERT INTO `plugin_example_test_data`
VALUES (92, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 192, 'yesNo_no', 'onOff_on', '', '第1列内容92',
        '第2列内容92', '第3列内容92', '第4列内容92', '第5列内容92');
INSERT INTO `plugin_example_test_data`
VALUES (93, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 193, 'yesNo_no', 'onOff_on', '', '第1列内容93',
        '第2列内容93', '第3列内容93', '第4列内容93', '第5列内容93');
INSERT INTO `plugin_example_test_data`
VALUES (94, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 194, 'yesNo_no', 'onOff_on', '', '第1列内容94',
        '第2列内容94', '第3列内容94', '第4列内容94', '第5列内容94');
INSERT INTO `plugin_example_test_data`
VALUES (95, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 195, 'yesNo_no', 'onOff_on', '', '第1列内容95',
        '第2列内容95', '第3列内容95', '第4列内容95', '第5列内容95');
INSERT INTO `plugin_example_test_data`
VALUES (96, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 196, 'yesNo_no', 'onOff_on', '', '第1列内容96',
        '第2列内容96', '第3列内容96', '第4列内容96', '第5列内容96');
INSERT INTO `plugin_example_test_data`
VALUES (97, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 197, 'yesNo_no', 'onOff_on', '', '第1列内容97',
        '第2列内容97', '第3列内容97', '第4列内容97', '第5列内容97');
INSERT INTO `plugin_example_test_data`
VALUES (98, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 198, 'yesNo_no', 'onOff_on', '', '第1列内容98',
        '第2列内容98', '第3列内容98', '第4列内容98', '第5列内容98');
INSERT INTO `plugin_example_test_data`
VALUES (99, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 199, 'yesNo_no', 'onOff_on', '', '第1列内容99',
        '第2列内容99', '第3列内容99', '第4列内容99', '第5列内容99');
INSERT INTO `plugin_example_test_data`
VALUES (100, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 200, 'yesNo_no', 'onOff_on', '',
        '第1列内容100', '第2列内容100', '第3列内容100', '第4列内容100', '第5列内容100');
INSERT INTO `plugin_example_test_data`
VALUES (101, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 201, 'yesNo_no', 'onOff_on', '',
        '第1列内容101', '第2列内容101', '第3列内容101', '第4列内容101', '第5列内容101');
INSERT INTO `plugin_example_test_data`
VALUES (102, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 202, 'yesNo_no', 'onOff_on', '',
        '第1列内容102', '第2列内容102', '第3列内容102', '第4列内容102', '第5列内容102');
INSERT INTO `plugin_example_test_data`
VALUES (103, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 203, 'yesNo_no', 'onOff_on', '',
        '第1列内容103', '第2列内容103', '第3列内容103', '第4列内容103', '第5列内容103');
INSERT INTO `plugin_example_test_data`
VALUES (104, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 204, 'yesNo_no', 'onOff_on', '',
        '第1列内容104', '第2列内容104', '第3列内容104', '第4列内容104', '第5列内容104');
INSERT INTO `plugin_example_test_data`
VALUES (105, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 205, 'yesNo_no', 'onOff_on', '',
        '第1列内容105', '第2列内容105', '第3列内容105', '第4列内容105', '第5列内容105');
INSERT INTO `plugin_example_test_data`
VALUES (106, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 206, 'yesNo_no', 'onOff_on', '',
        '第1列内容106', '第2列内容106', '第3列内容106', '第4列内容106', '第5列内容106');
INSERT INTO `plugin_example_test_data`
VALUES (107, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 207, 'yesNo_no', 'onOff_on', '',
        '第1列内容107', '第2列内容107', '第3列内容107', '第4列内容107', '第5列内容107');
INSERT INTO `plugin_example_test_data`
VALUES (108, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 208, 'yesNo_no', 'onOff_on', '',
        '第1列内容108', '第2列内容108', '第3列内容108', '第4列内容108', '第5列内容108');
INSERT INTO `plugin_example_test_data`
VALUES (109, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 209, 'yesNo_no', 'onOff_on', '',
        '第1列内容109', '第2列内容109', '第3列内容109', '第4列内容109', '第5列内容109');
INSERT INTO `plugin_example_test_data`
VALUES (110, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 210, 'yesNo_no', 'onOff_on', '',
        '第1列内容110', '第2列内容110', '第3列内容110', '第4列内容110', '第5列内容110');
INSERT INTO `plugin_example_test_data`
VALUES (111, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 211, 'yesNo_no', 'onOff_on', '',
        '第1列内容111', '第2列内容111', '第3列内容111', '第4列内容111', '第5列内容111');
INSERT INTO `plugin_example_test_data`
VALUES (112, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 212, 'yesNo_no', 'onOff_on', '',
        '第1列内容112', '第2列内容112', '第3列内容112', '第4列内容112', '第5列内容112');
INSERT INTO `plugin_example_test_data`
VALUES (113, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 213, 'yesNo_no', 'onOff_on', '',
        '第1列内容113', '第2列内容113', '第3列内容113', '第4列内容113', '第5列内容113');
INSERT INTO `plugin_example_test_data`
VALUES (114, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 214, 'yesNo_no', 'onOff_on', '',
        '第1列内容114', '第2列内容114', '第3列内容114', '第4列内容114', '第5列内容114');
INSERT INTO `plugin_example_test_data`
VALUES (115, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 215, 'yesNo_no', 'onOff_on', '',
        '第1列内容115', '第2列内容115', '第3列内容115', '第4列内容115', '第5列内容115');
INSERT INTO `plugin_example_test_data`
VALUES (116, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 216, 'yesNo_no', 'onOff_on', '',
        '第1列内容116', '第2列内容116', '第3列内容116', '第4列内容116', '第5列内容116');
INSERT INTO `plugin_example_test_data`
VALUES (117, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 217, 'yesNo_no', 'onOff_on', '',
        '第1列内容117', '第2列内容117', '第3列内容117', '第4列内容117', '第5列内容117');
INSERT INTO `plugin_example_test_data`
VALUES (118, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 218, 'yesNo_no', 'onOff_on', '',
        '第1列内容118', '第2列内容118', '第3列内容118', '第4列内容118', '第5列内容118');
INSERT INTO `plugin_example_test_data`
VALUES (119, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 219, 'yesNo_no', 'onOff_on', '',
        '第1列内容119', '第2列内容119', '第3列内容119', '第4列内容119', '第5列内容119');
INSERT INTO `plugin_example_test_data`
VALUES (120, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 220, 'yesNo_no', 'onOff_on', '',
        '第1列内容120', '第2列内容120', '第3列内容120', '第4列内容120', '第5列内容120');
INSERT INTO `plugin_example_test_data`
VALUES (121, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 221, 'yesNo_no', 'onOff_on', '',
        '第1列内容121', '第2列内容121', '第3列内容121', '第4列内容121', '第5列内容121');
INSERT INTO `plugin_example_test_data`
VALUES (122, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 222, 'yesNo_no', 'onOff_on', '',
        '第1列内容122', '第2列内容122', '第3列内容122', '第4列内容122', '第5列内容122');
INSERT INTO `plugin_example_test_data`
VALUES (123, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 223, 'yesNo_no', 'onOff_on', '',
        '第1列内容123', '第2列内容123', '第3列内容123', '第4列内容123', '第5列内容123');
INSERT INTO `plugin_example_test_data`
VALUES (124, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 224, 'yesNo_no', 'onOff_on', '',
        '第1列内容124', '第2列内容124', '第3列内容124', '第4列内容124', '第5列内容124');
INSERT INTO `plugin_example_test_data`
VALUES (125, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 225, 'yesNo_no', 'onOff_on', '',
        '第1列内容125', '第2列内容125', '第3列内容125', '第4列内容125', '第5列内容125');
INSERT INTO `plugin_example_test_data`
VALUES (126, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 226, 'yesNo_no', 'onOff_on', '',
        '第1列内容126', '第2列内容126', '第3列内容126', '第4列内容126', '第5列内容126');
INSERT INTO `plugin_example_test_data`
VALUES (127, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 227, 'yesNo_no', 'onOff_on', '',
        '第1列内容127', '第2列内容127', '第3列内容127', '第4列内容127', '第5列内容127');
INSERT INTO `plugin_example_test_data`
VALUES (128, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 228, 'yesNo_no', 'onOff_on', '',
        '第1列内容128', '第2列内容128', '第3列内容128', '第4列内容128', '第5列内容128');
INSERT INTO `plugin_example_test_data`
VALUES (129, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 229, 'yesNo_no', 'onOff_on', '',
        '第1列内容129', '第2列内容129', '第3列内容129', '第4列内容129', '第5列内容129');
INSERT INTO `plugin_example_test_data`
VALUES (130, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 230, 'yesNo_no', 'onOff_on', '',
        '第1列内容130', '第2列内容130', '第3列内容130', '第4列内容130', '第5列内容130');
INSERT INTO `plugin_example_test_data`
VALUES (131, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 231, 'yesNo_no', 'onOff_on', '',
        '第1列内容131', '第2列内容131', '第3列内容131', '第4列内容131', '第5列内容131');
INSERT INTO `plugin_example_test_data`
VALUES (132, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 232, 'yesNo_no', 'onOff_on', '',
        '第1列内容132', '第2列内容132', '第3列内容132', '第4列内容132', '第5列内容132');
INSERT INTO `plugin_example_test_data`
VALUES (133, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 233, 'yesNo_no', 'onOff_on', '',
        '第1列内容133', '第2列内容133', '第3列内容133', '第4列内容133', '第5列内容133');
INSERT INTO `plugin_example_test_data`
VALUES (134, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 234, 'yesNo_no', 'onOff_on', '',
        '第1列内容134', '第2列内容134', '第3列内容134', '第4列内容134', '第5列内容134');
INSERT INTO `plugin_example_test_data`
VALUES (135, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 235, 'yesNo_no', 'onOff_on', '',
        '第1列内容135', '第2列内容135', '第3列内容135', '第4列内容135', '第5列内容135');
INSERT INTO `plugin_example_test_data`
VALUES (136, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 236, 'yesNo_no', 'onOff_on', '',
        '第1列内容136', '第2列内容136', '第3列内容136', '第4列内容136', '第5列内容136');
INSERT INTO `plugin_example_test_data`
VALUES (137, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 237, 'yesNo_no', 'onOff_on', '',
        '第1列内容137', '第2列内容137', '第3列内容137', '第4列内容137', '第5列内容137');
INSERT INTO `plugin_example_test_data`
VALUES (138, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 238, 'yesNo_no', 'onOff_on', '',
        '第1列内容138', '第2列内容138', '第3列内容138', '第4列内容138', '第5列内容138');
INSERT INTO `plugin_example_test_data`
VALUES (139, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 239, 'yesNo_no', 'onOff_on', '',
        '第1列内容139', '第2列内容139', '第3列内容139', '第4列内容139', '第5列内容139');
INSERT INTO `plugin_example_test_data`
VALUES (140, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 240, 'yesNo_no', 'onOff_on', '',
        '第1列内容140', '第2列内容140', '第3列内容140', '第4列内容140', '第5列内容140');
INSERT INTO `plugin_example_test_data`
VALUES (141, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 241, 'yesNo_no', 'onOff_on', '',
        '第1列内容141', '第2列内容141', '第3列内容141', '第4列内容141', '第5列内容141');
INSERT INTO `plugin_example_test_data`
VALUES (142, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 242, 'yesNo_no', 'onOff_on', '',
        '第1列内容142', '第2列内容142', '第3列内容142', '第4列内容142', '第5列内容142');
INSERT INTO `plugin_example_test_data`
VALUES (143, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 243, 'yesNo_no', 'onOff_on', '',
        '第1列内容143', '第2列内容143', '第3列内容143', '第4列内容143', '第5列内容143');
INSERT INTO `plugin_example_test_data`
VALUES (144, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 244, 'yesNo_no', 'onOff_on', '',
        '第1列内容144', '第2列内容144', '第3列内容144', '第4列内容144', '第5列内容144');
INSERT INTO `plugin_example_test_data`
VALUES (145, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 245, 'yesNo_no', 'onOff_on', '',
        '第1列内容145', '第2列内容145', '第3列内容145', '第4列内容145', '第5列内容145');
INSERT INTO `plugin_example_test_data`
VALUES (146, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 246, 'yesNo_no', 'onOff_on', '',
        '第1列内容146', '第2列内容146', '第3列内容146', '第4列内容146', '第5列内容146');
INSERT INTO `plugin_example_test_data`
VALUES (147, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 247, 'yesNo_no', 'onOff_on', '',
        '第1列内容147', '第2列内容147', '第3列内容147', '第4列内容147', '第5列内容147');
INSERT INTO `plugin_example_test_data`
VALUES (148, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 248, 'yesNo_no', 'onOff_on', '',
        '第1列内容148', '第2列内容148', '第3列内容148', '第4列内容148', '第5列内容148');
INSERT INTO `plugin_example_test_data`
VALUES (149, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 249, 'yesNo_no', 'onOff_on', '',
        '第1列内容149', '第2列内容149', '第3列内容149', '第4列内容149', '第5列内容149');
INSERT INTO `plugin_example_test_data`
VALUES (150, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 250, 'yesNo_no', 'onOff_on', '',
        '第1列内容150', '第2列内容150', '第3列内容150', '第4列内容150', '第5列内容150');
INSERT INTO `plugin_example_test_data`
VALUES (151, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 251, 'yesNo_no', 'onOff_on', '',
        '第1列内容151', '第2列内容151', '第3列内容151', '第4列内容151', '第5列内容151');
INSERT INTO `plugin_example_test_data`
VALUES (152, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 252, 'yesNo_no', 'onOff_on', '',
        '第1列内容152', '第2列内容152', '第3列内容152', '第4列内容152', '第5列内容152');
INSERT INTO `plugin_example_test_data`
VALUES (153, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 253, 'yesNo_no', 'onOff_on', '',
        '第1列内容153', '第2列内容153', '第3列内容153', '第4列内容153', '第5列内容153');
INSERT INTO `plugin_example_test_data`
VALUES (154, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 254, 'yesNo_no', 'onOff_on', '',
        '第1列内容154', '第2列内容154', '第3列内容154', '第4列内容154', '第5列内容154');
INSERT INTO `plugin_example_test_data`
VALUES (155, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 255, 'yesNo_no', 'onOff_on', '',
        '第1列内容155', '第2列内容155', '第3列内容155', '第4列内容155', '第5列内容155');
INSERT INTO `plugin_example_test_data`
VALUES (156, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 256, 'yesNo_no', 'onOff_on', '',
        '第1列内容156', '第2列内容156', '第3列内容156', '第4列内容156', '第5列内容156');
INSERT INTO `plugin_example_test_data`
VALUES (157, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 257, 'yesNo_no', 'onOff_on', '',
        '第1列内容157', '第2列内容157', '第3列内容157', '第4列内容157', '第5列内容157');
INSERT INTO `plugin_example_test_data`
VALUES (158, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 258, 'yesNo_no', 'onOff_on', '',
        '第1列内容158', '第2列内容158', '第3列内容158', '第4列内容158', '第5列内容158');
INSERT INTO `plugin_example_test_data`
VALUES (159, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 259, 'yesNo_no', 'onOff_on', '',
        '第1列内容159', '第2列内容159', '第3列内容159', '第4列内容159', '第5列内容159');
INSERT INTO `plugin_example_test_data`
VALUES (160, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 260, 'yesNo_no', 'onOff_on', '',
        '第1列内容160', '第2列内容160', '第3列内容160', '第4列内容160', '第5列内容160');
INSERT INTO `plugin_example_test_data`
VALUES (161, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 261, 'yesNo_no', 'onOff_on', '',
        '第1列内容161', '第2列内容161', '第3列内容161', '第4列内容161', '第5列内容161');
INSERT INTO `plugin_example_test_data`
VALUES (162, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 262, 'yesNo_no', 'onOff_on', '',
        '第1列内容162', '第2列内容162', '第3列内容162', '第4列内容162', '第5列内容162');
INSERT INTO `plugin_example_test_data`
VALUES (163, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 263, 'yesNo_no', 'onOff_on', '',
        '第1列内容163', '第2列内容163', '第3列内容163', '第4列内容163', '第5列内容163');
INSERT INTO `plugin_example_test_data`
VALUES (164, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 264, 'yesNo_no', 'onOff_on', '',
        '第1列内容164', '第2列内容164', '第3列内容164', '第4列内容164', '第5列内容164');
INSERT INTO `plugin_example_test_data`
VALUES (165, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 265, 'yesNo_no', 'onOff_on', '',
        '第1列内容165', '第2列内容165', '第3列内容165', '第4列内容165', '第5列内容165');
INSERT INTO `plugin_example_test_data`
VALUES (166, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 266, 'yesNo_no', 'onOff_on', '',
        '第1列内容166', '第2列内容166', '第3列内容166', '第4列内容166', '第5列内容166');
INSERT INTO `plugin_example_test_data`
VALUES (167, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 267, 'yesNo_no', 'onOff_on', '',
        '第1列内容167', '第2列内容167', '第3列内容167', '第4列内容167', '第5列内容167');
INSERT INTO `plugin_example_test_data`
VALUES (168, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 268, 'yesNo_no', 'onOff_on', '',
        '第1列内容168', '第2列内容168', '第3列内容168', '第4列内容168', '第5列内容168');
INSERT INTO `plugin_example_test_data`
VALUES (169, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 269, 'yesNo_no', 'onOff_on', '',
        '第1列内容169', '第2列内容169', '第3列内容169', '第4列内容169', '第5列内容169');
INSERT INTO `plugin_example_test_data`
VALUES (170, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 270, 'yesNo_no', 'onOff_on', '',
        '第1列内容170', '第2列内容170', '第3列内容170', '第4列内容170', '第5列内容170');
INSERT INTO `plugin_example_test_data`
VALUES (171, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 271, 'yesNo_no', 'onOff_on', '',
        '第1列内容171', '第2列内容171', '第3列内容171', '第4列内容171', '第5列内容171');
INSERT INTO `plugin_example_test_data`
VALUES (172, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 272, 'yesNo_no', 'onOff_on', '',
        '第1列内容172', '第2列内容172', '第3列内容172', '第4列内容172', '第5列内容172');
INSERT INTO `plugin_example_test_data`
VALUES (173, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 273, 'yesNo_no', 'onOff_on', '',
        '第1列内容173', '第2列内容173', '第3列内容173', '第4列内容173', '第5列内容173');
INSERT INTO `plugin_example_test_data`
VALUES (174, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 274, 'yesNo_no', 'onOff_on', '',
        '第1列内容174', '第2列内容174', '第3列内容174', '第4列内容174', '第5列内容174');
INSERT INTO `plugin_example_test_data`
VALUES (175, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 275, 'yesNo_no', 'onOff_on', '',
        '第1列内容175', '第2列内容175', '第3列内容175', '第4列内容175', '第5列内容175');
INSERT INTO `plugin_example_test_data`
VALUES (176, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 276, 'yesNo_no', 'onOff_on', '',
        '第1列内容176', '第2列内容176', '第3列内容176', '第4列内容176', '第5列内容176');
INSERT INTO `plugin_example_test_data`
VALUES (177, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 277, 'yesNo_no', 'onOff_on', '',
        '第1列内容177', '第2列内容177', '第3列内容177', '第4列内容177', '第5列内容177');
INSERT INTO `plugin_example_test_data`
VALUES (178, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 278, 'yesNo_no', 'onOff_on', '',
        '第1列内容178', '第2列内容178', '第3列内容178', '第4列内容178', '第5列内容178');
INSERT INTO `plugin_example_test_data`
VALUES (179, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 279, 'yesNo_no', 'onOff_on', '',
        '第1列内容179', '第2列内容179', '第3列内容179', '第4列内容179', '第5列内容179');
INSERT INTO `plugin_example_test_data`
VALUES (180, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 280, 'yesNo_no', 'onOff_on', '',
        '第1列内容180', '第2列内容180', '第3列内容180', '第4列内容180', '第5列内容180');
INSERT INTO `plugin_example_test_data`
VALUES (181, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 281, 'yesNo_no', 'onOff_on', '',
        '第1列内容181', '第2列内容181', '第3列内容181', '第4列内容181', '第5列内容181');
INSERT INTO `plugin_example_test_data`
VALUES (182, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 282, 'yesNo_no', 'onOff_on', '',
        '第1列内容182', '第2列内容182', '第3列内容182', '第4列内容182', '第5列内容182');
INSERT INTO `plugin_example_test_data`
VALUES (183, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 283, 'yesNo_no', 'onOff_on', '',
        '第1列内容183', '第2列内容183', '第3列内容183', '第4列内容183', '第5列内容183');
INSERT INTO `plugin_example_test_data`
VALUES (184, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 284, 'yesNo_no', 'onOff_on', '',
        '第1列内容184', '第2列内容184', '第3列内容184', '第4列内容184', '第5列内容184');
INSERT INTO `plugin_example_test_data`
VALUES (185, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 285, 'yesNo_no', 'onOff_on', '',
        '第1列内容185', '第2列内容185', '第3列内容185', '第4列内容185', '第5列内容185');
INSERT INTO `plugin_example_test_data`
VALUES (186, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 286, 'yesNo_no', 'onOff_on', '',
        '第1列内容186', '第2列内容186', '第3列内容186', '第4列内容186', '第5列内容186');
INSERT INTO `plugin_example_test_data`
VALUES (187, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 287, 'yesNo_no', 'onOff_on', '',
        '第1列内容187', '第2列内容187', '第3列内容187', '第4列内容187', '第5列内容187');
INSERT INTO `plugin_example_test_data`
VALUES (188, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 288, 'yesNo_no', 'onOff_on', '',
        '第1列内容188', '第2列内容188', '第3列内容188', '第4列内容188', '第5列内容188');
INSERT INTO `plugin_example_test_data`
VALUES (189, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 289, 'yesNo_no', 'onOff_on', '',
        '第1列内容189', '第2列内容189', '第3列内容189', '第4列内容189', '第5列内容189');
INSERT INTO `plugin_example_test_data`
VALUES (190, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 290, 'yesNo_no', 'onOff_on', '',
        '第1列内容190', '第2列内容190', '第3列内容190', '第4列内容190', '第5列内容190');
INSERT INTO `plugin_example_test_data`
VALUES (191, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 291, 'yesNo_no', 'onOff_on', '',
        '第1列内容191', '第2列内容191', '第3列内容191', '第4列内容191', '第5列内容191');
INSERT INTO `plugin_example_test_data`
VALUES (192, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 292, 'yesNo_no', 'onOff_on', '',
        '第1列内容192', '第2列内容192', '第3列内容192', '第4列内容192', '第5列内容192');
INSERT INTO `plugin_example_test_data`
VALUES (193, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 293, 'yesNo_no', 'onOff_on', '',
        '第1列内容193', '第2列内容193', '第3列内容193', '第4列内容193', '第5列内容193');
INSERT INTO `plugin_example_test_data`
VALUES (194, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 294, 'yesNo_no', 'onOff_on', '',
        '第1列内容194', '第2列内容194', '第3列内容194', '第4列内容194', '第5列内容194');
INSERT INTO `plugin_example_test_data`
VALUES (195, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 295, 'yesNo_no', 'onOff_on', '',
        '第1列内容195', '第2列内容195', '第3列内容195', '第4列内容195', '第5列内容195');
INSERT INTO `plugin_example_test_data`
VALUES (196, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 296, 'yesNo_no', 'onOff_on', '',
        '第1列内容196', '第2列内容196', '第3列内容196', '第4列内容196', '第5列内容196');
INSERT INTO `plugin_example_test_data`
VALUES (197, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 297, 'yesNo_no', 'onOff_on', '',
        '第1列内容197', '第2列内容197', '第3列内容197', '第4列内容197', '第5列内容197');
INSERT INTO `plugin_example_test_data`
VALUES (198, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 298, 'yesNo_no', 'onOff_on', '',
        '第1列内容198', '第2列内容198', '第3列内容198', '第4列内容198', '第5列内容198');
INSERT INTO `plugin_example_test_data`
VALUES (199, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 299, 'yesNo_no', 'onOff_on', '',
        '第1列内容199', '第2列内容199', '第3列内容199', '第4列内容199', '第5列内容199');
INSERT INTO `plugin_example_test_data`
VALUES (200, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 300, 'yesNo_no', 'onOff_on', '',
        '第1列内容200', '第2列内容200', '第3列内容200', '第4列内容200', '第5列内容200');
INSERT INTO `plugin_example_test_data`
VALUES (201, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 301, 'yesNo_no', 'onOff_on', '',
        '第1列内容201', '第2列内容201', '第3列内容201', '第4列内容201', '第5列内容201');
INSERT INTO `plugin_example_test_data`
VALUES (202, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 302, 'yesNo_no', 'onOff_on', '',
        '第1列内容202', '第2列内容202', '第3列内容202', '第4列内容202', '第5列内容202');
INSERT INTO `plugin_example_test_data`
VALUES (203, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 303, 'yesNo_no', 'onOff_on', '',
        '第1列内容203', '第2列内容203', '第3列内容203', '第4列内容203', '第5列内容203');
INSERT INTO `plugin_example_test_data`
VALUES (204, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 304, 'yesNo_no', 'onOff_on', '',
        '第1列内容204', '第2列内容204', '第3列内容204', '第4列内容204', '第5列内容204');
INSERT INTO `plugin_example_test_data`
VALUES (205, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 305, 'yesNo_no', 'onOff_on', '',
        '第1列内容205', '第2列内容205', '第3列内容205', '第4列内容205', '第5列内容205');
INSERT INTO `plugin_example_test_data`
VALUES (206, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 306, 'yesNo_no', 'onOff_on', '',
        '第1列内容206', '第2列内容206', '第3列内容206', '第4列内容206', '第5列内容206');
INSERT INTO `plugin_example_test_data`
VALUES (207, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 307, 'yesNo_no', 'onOff_on', '',
        '第1列内容207', '第2列内容207', '第3列内容207', '第4列内容207', '第5列内容207');
INSERT INTO `plugin_example_test_data`
VALUES (208, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 308, 'yesNo_no', 'onOff_on', '',
        '第1列内容208', '第2列内容208', '第3列内容208', '第4列内容208', '第5列内容208');
INSERT INTO `plugin_example_test_data`
VALUES (209, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 309, 'yesNo_no', 'onOff_on', '',
        '第1列内容209', '第2列内容209', '第3列内容209', '第4列内容209', '第5列内容209');
INSERT INTO `plugin_example_test_data`
VALUES (210, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 310, 'yesNo_no', 'onOff_on', '',
        '第1列内容210', '第2列内容210', '第3列内容210', '第4列内容210', '第5列内容210');
INSERT INTO `plugin_example_test_data`
VALUES (211, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 311, 'yesNo_no', 'onOff_on', '',
        '第1列内容211', '第2列内容211', '第3列内容211', '第4列内容211', '第5列内容211');
INSERT INTO `plugin_example_test_data`
VALUES (212, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 312, 'yesNo_no', 'onOff_on', '',
        '第1列内容212', '第2列内容212', '第3列内容212', '第4列内容212', '第5列内容212');
INSERT INTO `plugin_example_test_data`
VALUES (213, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 313, 'yesNo_no', 'onOff_on', '',
        '第1列内容213', '第2列内容213', '第3列内容213', '第4列内容213', '第5列内容213');
INSERT INTO `plugin_example_test_data`
VALUES (214, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 314, 'yesNo_no', 'onOff_on', '',
        '第1列内容214', '第2列内容214', '第3列内容214', '第4列内容214', '第5列内容214');
INSERT INTO `plugin_example_test_data`
VALUES (215, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 315, 'yesNo_no', 'onOff_on', '',
        '第1列内容215', '第2列内容215', '第3列内容215', '第4列内容215', '第5列内容215');
INSERT INTO `plugin_example_test_data`
VALUES (216, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 316, 'yesNo_no', 'onOff_on', '',
        '第1列内容216', '第2列内容216', '第3列内容216', '第4列内容216', '第5列内容216');
INSERT INTO `plugin_example_test_data`
VALUES (217, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 317, 'yesNo_no', 'onOff_on', '',
        '第1列内容217', '第2列内容217', '第3列内容217', '第4列内容217', '第5列内容217');
INSERT INTO `plugin_example_test_data`
VALUES (218, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 318, 'yesNo_no', 'onOff_on', '',
        '第1列内容218', '第2列内容218', '第3列内容218', '第4列内容218', '第5列内容218');
INSERT INTO `plugin_example_test_data`
VALUES (219, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 319, 'yesNo_no', 'onOff_on', '',
        '第1列内容219', '第2列内容219', '第3列内容219', '第4列内容219', '第5列内容219');
INSERT INTO `plugin_example_test_data`
VALUES (220, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 320, 'yesNo_no', 'onOff_on', '',
        '第1列内容220', '第2列内容220', '第3列内容220', '第4列内容220', '第5列内容220');
INSERT INTO `plugin_example_test_data`
VALUES (221, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 321, 'yesNo_no', 'onOff_on', '',
        '第1列内容221', '第2列内容221', '第3列内容221', '第4列内容221', '第5列内容221');
INSERT INTO `plugin_example_test_data`
VALUES (222, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 322, 'yesNo_no', 'onOff_on', '',
        '第1列内容222', '第2列内容222', '第3列内容222', '第4列内容222', '第5列内容222');
INSERT INTO `plugin_example_test_data`
VALUES (223, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 323, 'yesNo_no', 'onOff_on', '',
        '第1列内容223', '第2列内容223', '第3列内容223', '第4列内容223', '第5列内容223');
INSERT INTO `plugin_example_test_data`
VALUES (224, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 324, 'yesNo_no', 'onOff_on', '',
        '第1列内容224', '第2列内容224', '第3列内容224', '第4列内容224', '第5列内容224');
INSERT INTO `plugin_example_test_data`
VALUES (225, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 325, 'yesNo_no', 'onOff_on', '',
        '第1列内容225', '第2列内容225', '第3列内容225', '第4列内容225', '第5列内容225');
INSERT INTO `plugin_example_test_data`
VALUES (226, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 326, 'yesNo_no', 'onOff_on', '',
        '第1列内容226', '第2列内容226', '第3列内容226', '第4列内容226', '第5列内容226');
INSERT INTO `plugin_example_test_data`
VALUES (227, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 327, 'yesNo_no', 'onOff_on', '',
        '第1列内容227', '第2列内容227', '第3列内容227', '第4列内容227', '第5列内容227');
INSERT INTO `plugin_example_test_data`
VALUES (228, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 328, 'yesNo_no', 'onOff_on', '',
        '第1列内容228', '第2列内容228', '第3列内容228', '第4列内容228', '第5列内容228');
INSERT INTO `plugin_example_test_data`
VALUES (229, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 329, 'yesNo_no', 'onOff_on', '',
        '第1列内容229', '第2列内容229', '第3列内容229', '第4列内容229', '第5列内容229');
INSERT INTO `plugin_example_test_data`
VALUES (230, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 330, 'yesNo_no', 'onOff_on', '',
        '第1列内容230', '第2列内容230', '第3列内容230', '第4列内容230', '第5列内容230');
INSERT INTO `plugin_example_test_data`
VALUES (231, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 331, 'yesNo_no', 'onOff_on', '',
        '第1列内容231', '第2列内容231', '第3列内容231', '第4列内容231', '第5列内容231');
INSERT INTO `plugin_example_test_data`
VALUES (232, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 332, 'yesNo_no', 'onOff_on', '',
        '第1列内容232', '第2列内容232', '第3列内容232', '第4列内容232', '第5列内容232');
INSERT INTO `plugin_example_test_data`
VALUES (233, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 333, 'yesNo_no', 'onOff_on', '',
        '第1列内容233', '第2列内容233', '第3列内容233', '第4列内容233', '第5列内容233');
INSERT INTO `plugin_example_test_data`
VALUES (234, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 334, 'yesNo_no', 'onOff_on', '',
        '第1列内容234', '第2列内容234', '第3列内容234', '第4列内容234', '第5列内容234');
INSERT INTO `plugin_example_test_data`
VALUES (235, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 335, 'yesNo_no', 'onOff_on', '',
        '第1列内容235', '第2列内容235', '第3列内容235', '第4列内容235', '第5列内容235');
INSERT INTO `plugin_example_test_data`
VALUES (236, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 336, 'yesNo_no', 'onOff_on', '',
        '第1列内容236', '第2列内容236', '第3列内容236', '第4列内容236', '第5列内容236');
INSERT INTO `plugin_example_test_data`
VALUES (237, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 337, 'yesNo_no', 'onOff_on', '',
        '第1列内容237', '第2列内容237', '第3列内容237', '第4列内容237', '第5列内容237');
INSERT INTO `plugin_example_test_data`
VALUES (238, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 338, 'yesNo_no', 'onOff_on', '',
        '第1列内容238', '第2列内容238', '第3列内容238', '第4列内容238', '第5列内容238');
INSERT INTO `plugin_example_test_data`
VALUES (239, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 339, 'yesNo_no', 'onOff_on', '',
        '第1列内容239', '第2列内容239', '第3列内容239', '第4列内容239', '第5列内容239');
INSERT INTO `plugin_example_test_data`
VALUES (240, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 340, 'yesNo_no', 'onOff_on', '',
        '第1列内容240', '第2列内容240', '第3列内容240', '第4列内容240', '第5列内容240');
INSERT INTO `plugin_example_test_data`
VALUES (241, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 341, 'yesNo_no', 'onOff_on', '',
        '第1列内容241', '第2列内容241', '第3列内容241', '第4列内容241', '第5列内容241');
INSERT INTO `plugin_example_test_data`
VALUES (242, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 342, 'yesNo_no', 'onOff_on', '',
        '第1列内容242', '第2列内容242', '第3列内容242', '第4列内容242', '第5列内容242');
INSERT INTO `plugin_example_test_data`
VALUES (243, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 343, 'yesNo_no', 'onOff_on', '',
        '第1列内容243', '第2列内容243', '第3列内容243', '第4列内容243', '第5列内容243');
INSERT INTO `plugin_example_test_data`
VALUES (244, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 344, 'yesNo_no', 'onOff_on', '',
        '第1列内容244', '第2列内容244', '第3列内容244', '第4列内容244', '第5列内容244');
INSERT INTO `plugin_example_test_data`
VALUES (245, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 345, 'yesNo_no', 'onOff_on', '',
        '第1列内容245', '第2列内容245', '第3列内容245', '第4列内容245', '第5列内容245');
INSERT INTO `plugin_example_test_data`
VALUES (246, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 346, 'yesNo_no', 'onOff_on', '',
        '第1列内容246', '第2列内容246', '第3列内容246', '第4列内容246', '第5列内容246');
INSERT INTO `plugin_example_test_data`
VALUES (247, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 347, 'yesNo_no', 'onOff_on', '',
        '第1列内容247', '第2列内容247', '第3列内容247', '第4列内容247', '第5列内容247');
INSERT INTO `plugin_example_test_data`
VALUES (248, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 348, 'yesNo_no', 'onOff_on', '',
        '第1列内容248', '第2列内容248', '第3列内容248', '第4列内容248', '第5列内容248');
INSERT INTO `plugin_example_test_data`
VALUES (249, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 349, 'yesNo_no', 'onOff_on', '',
        '第1列内容249', '第2列内容249', '第3列内容249', '第4列内容249', '第5列内容249');
INSERT INTO `plugin_example_test_data`
VALUES (250, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 350, 'yesNo_no', 'onOff_on', '',
        '第1列内容250', '第2列内容250', '第3列内容250', '第4列内容250', '第5列内容250');
INSERT INTO `plugin_example_test_data`
VALUES (251, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 351, 'yesNo_no', 'onOff_on', '',
        '第1列内容251', '第2列内容251', '第3列内容251', '第4列内容251', '第5列内容251');
INSERT INTO `plugin_example_test_data`
VALUES (252, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 352, 'yesNo_no', 'onOff_on', '',
        '第1列内容252', '第2列内容252', '第3列内容252', '第4列内容252', '第5列内容252');
INSERT INTO `plugin_example_test_data`
VALUES (253, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 353, 'yesNo_no', 'onOff_on', '',
        '第1列内容253', '第2列内容253', '第3列内容253', '第4列内容253', '第5列内容253');
INSERT INTO `plugin_example_test_data`
VALUES (254, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 354, 'yesNo_no', 'onOff_on', '',
        '第1列内容254', '第2列内容254', '第3列内容254', '第4列内容254', '第5列内容254');
INSERT INTO `plugin_example_test_data`
VALUES (255, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 355, 'yesNo_no', 'onOff_on', '',
        '第1列内容255', '第2列内容255', '第3列内容255', '第4列内容255', '第5列内容255');
INSERT INTO `plugin_example_test_data`
VALUES (256, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 356, 'yesNo_no', 'onOff_on', '',
        '第1列内容256', '第2列内容256', '第3列内容256', '第4列内容256', '第5列内容256');
INSERT INTO `plugin_example_test_data`
VALUES (257, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 357, 'yesNo_no', 'onOff_on', '',
        '第1列内容257', '第2列内容257', '第3列内容257', '第4列内容257', '第5列内容257');
INSERT INTO `plugin_example_test_data`
VALUES (258, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 358, 'yesNo_no', 'onOff_on', '',
        '第1列内容258', '第2列内容258', '第3列内容258', '第4列内容258', '第5列内容258');
INSERT INTO `plugin_example_test_data`
VALUES (259, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 359, 'yesNo_no', 'onOff_on', '',
        '第1列内容259', '第2列内容259', '第3列内容259', '第4列内容259', '第5列内容259');
INSERT INTO `plugin_example_test_data`
VALUES (260, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 360, 'yesNo_no', 'onOff_on', '',
        '第1列内容260', '第2列内容260', '第3列内容260', '第4列内容260', '第5列内容260');
INSERT INTO `plugin_example_test_data`
VALUES (261, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 361, 'yesNo_no', 'onOff_on', '',
        '第1列内容261', '第2列内容261', '第3列内容261', '第4列内容261', '第5列内容261');
INSERT INTO `plugin_example_test_data`
VALUES (262, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 362, 'yesNo_no', 'onOff_on', '',
        '第1列内容262', '第2列内容262', '第3列内容262', '第4列内容262', '第5列内容262');
INSERT INTO `plugin_example_test_data`
VALUES (263, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 363, 'yesNo_no', 'onOff_on', '',
        '第1列内容263', '第2列内容263', '第3列内容263', '第4列内容263', '第5列内容263');
INSERT INTO `plugin_example_test_data`
VALUES (264, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 364, 'yesNo_no', 'onOff_on', '',
        '第1列内容264', '第2列内容264', '第3列内容264', '第4列内容264', '第5列内容264');
INSERT INTO `plugin_example_test_data`
VALUES (265, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 365, 'yesNo_no', 'onOff_on', '',
        '第1列内容265', '第2列内容265', '第3列内容265', '第4列内容265', '第5列内容265');
INSERT INTO `plugin_example_test_data`
VALUES (266, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 366, 'yesNo_no', 'onOff_on', '',
        '第1列内容266', '第2列内容266', '第3列内容266', '第4列内容266', '第5列内容266');
INSERT INTO `plugin_example_test_data`
VALUES (267, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 367, 'yesNo_no', 'onOff_on', '',
        '第1列内容267', '第2列内容267', '第3列内容267', '第4列内容267', '第5列内容267');
INSERT INTO `plugin_example_test_data`
VALUES (268, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 368, 'yesNo_no', 'onOff_on', '',
        '第1列内容268', '第2列内容268', '第3列内容268', '第4列内容268', '第5列内容268');
INSERT INTO `plugin_example_test_data`
VALUES (269, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 369, 'yesNo_no', 'onOff_on', '',
        '第1列内容269', '第2列内容269', '第3列内容269', '第4列内容269', '第5列内容269');
INSERT INTO `plugin_example_test_data`
VALUES (270, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 370, 'yesNo_no', 'onOff_on', '',
        '第1列内容270', '第2列内容270', '第3列内容270', '第4列内容270', '第5列内容270');
INSERT INTO `plugin_example_test_data`
VALUES (271, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 371, 'yesNo_no', 'onOff_on', '',
        '第1列内容271', '第2列内容271', '第3列内容271', '第4列内容271', '第5列内容271');
INSERT INTO `plugin_example_test_data`
VALUES (272, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 372, 'yesNo_no', 'onOff_on', '',
        '第1列内容272', '第2列内容272', '第3列内容272', '第4列内容272', '第5列内容272');
INSERT INTO `plugin_example_test_data`
VALUES (273, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 373, 'yesNo_no', 'onOff_on', '',
        '第1列内容273', '第2列内容273', '第3列内容273', '第4列内容273', '第5列内容273');
INSERT INTO `plugin_example_test_data`
VALUES (274, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 374, 'yesNo_no', 'onOff_on', '',
        '第1列内容274', '第2列内容274', '第3列内容274', '第4列内容274', '第5列内容274');
INSERT INTO `plugin_example_test_data`
VALUES (275, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 375, 'yesNo_no', 'onOff_on', '',
        '第1列内容275', '第2列内容275', '第3列内容275', '第4列内容275', '第5列内容275');
INSERT INTO `plugin_example_test_data`
VALUES (276, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 376, 'yesNo_no', 'onOff_on', '',
        '第1列内容276', '第2列内容276', '第3列内容276', '第4列内容276', '第5列内容276');
INSERT INTO `plugin_example_test_data`
VALUES (277, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 377, 'yesNo_no', 'onOff_on', '',
        '第1列内容277', '第2列内容277', '第3列内容277', '第4列内容277', '第5列内容277');
INSERT INTO `plugin_example_test_data`
VALUES (278, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 378, 'yesNo_no', 'onOff_on', '',
        '第1列内容278', '第2列内容278', '第3列内容278', '第4列内容278', '第5列内容278');
INSERT INTO `plugin_example_test_data`
VALUES (279, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 379, 'yesNo_no', 'onOff_on', '',
        '第1列内容279', '第2列内容279', '第3列内容279', '第4列内容279', '第5列内容279');
INSERT INTO `plugin_example_test_data`
VALUES (280, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 380, 'yesNo_no', 'onOff_on', '',
        '第1列内容280', '第2列内容280', '第3列内容280', '第4列内容280', '第5列内容280');
INSERT INTO `plugin_example_test_data`
VALUES (281, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 381, 'yesNo_no', 'onOff_on', '',
        '第1列内容281', '第2列内容281', '第3列内容281', '第4列内容281', '第5列内容281');
INSERT INTO `plugin_example_test_data`
VALUES (282, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 382, 'yesNo_no', 'onOff_on', '',
        '第1列内容282', '第2列内容282', '第3列内容282', '第4列内容282', '第5列内容282');
INSERT INTO `plugin_example_test_data`
VALUES (283, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 383, 'yesNo_no', 'onOff_on', '',
        '第1列内容283', '第2列内容283', '第3列内容283', '第4列内容283', '第5列内容283');
INSERT INTO `plugin_example_test_data`
VALUES (284, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 384, 'yesNo_no', 'onOff_on', '',
        '第1列内容284', '第2列内容284', '第3列内容284', '第4列内容284', '第5列内容284');
INSERT INTO `plugin_example_test_data`
VALUES (285, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 385, 'yesNo_no', 'onOff_on', '',
        '第1列内容285', '第2列内容285', '第3列内容285', '第4列内容285', '第5列内容285');
INSERT INTO `plugin_example_test_data`
VALUES (286, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 386, 'yesNo_no', 'onOff_on', '',
        '第1列内容286', '第2列内容286', '第3列内容286', '第4列内容286', '第5列内容286');
INSERT INTO `plugin_example_test_data`
VALUES (287, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 387, 'yesNo_no', 'onOff_on', '',
        '第1列内容287', '第2列内容287', '第3列内容287', '第4列内容287', '第5列内容287');
INSERT INTO `plugin_example_test_data`
VALUES (288, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 388, 'yesNo_no', 'onOff_on', '',
        '第1列内容288', '第2列内容288', '第3列内容288', '第4列内容288', '第5列内容288');
INSERT INTO `plugin_example_test_data`
VALUES (289, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 389, 'yesNo_no', 'onOff_on', '',
        '第1列内容289', '第2列内容289', '第3列内容289', '第4列内容289', '第5列内容289');
INSERT INTO `plugin_example_test_data`
VALUES (290, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 390, 'yesNo_no', 'onOff_on', '',
        '第1列内容290', '第2列内容290', '第3列内容290', '第4列内容290', '第5列内容290');
INSERT INTO `plugin_example_test_data`
VALUES (291, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 391, 'yesNo_no', 'onOff_on', '',
        '第1列内容291', '第2列内容291', '第3列内容291', '第4列内容291', '第5列内容291');
INSERT INTO `plugin_example_test_data`
VALUES (292, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 392, 'yesNo_no', 'onOff_on', '',
        '第1列内容292', '第2列内容292', '第3列内容292', '第4列内容292', '第5列内容292');
INSERT INTO `plugin_example_test_data`
VALUES (293, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 393, 'yesNo_no', 'onOff_on', '',
        '第1列内容293', '第2列内容293', '第3列内容293', '第4列内容293', '第5列内容293');
INSERT INTO `plugin_example_test_data`
VALUES (294, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 394, 'yesNo_no', 'onOff_on', '',
        '第1列内容294', '第2列内容294', '第3列内容294', '第4列内容294', '第5列内容294');
INSERT INTO `plugin_example_test_data`
VALUES (295, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 395, 'yesNo_no', 'onOff_on', '',
        '第1列内容295', '第2列内容295', '第3列内容295', '第4列内容295', '第5列内容295');
INSERT INTO `plugin_example_test_data`
VALUES (296, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 396, 'yesNo_no', 'onOff_on', '',
        '第1列内容296', '第2列内容296', '第3列内容296', '第4列内容296', '第5列内容296');
INSERT INTO `plugin_example_test_data`
VALUES (297, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 397, 'yesNo_no', 'onOff_on', '',
        '第1列内容297', '第2列内容297', '第3列内容297', '第4列内容297', '第5列内容297');
INSERT INTO `plugin_example_test_data`
VALUES (298, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 398, 'yesNo_no', 'onOff_on', '',
        '第1列内容298', '第2列内容298', '第3列内容298', '第4列内容298', '第5列内容298');
INSERT INTO `plugin_example_test_data`
VALUES (299, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 399, 'yesNo_no', 'onOff_on', '',
        '第1列内容299', '第2列内容299', '第3列内容299', '第4列内容299', '第5列内容299');
INSERT INTO `plugin_example_test_data`
VALUES (300, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 400, 'yesNo_no', 'onOff_on', '',
        '第1列内容300', '第2列内容300', '第3列内容300', '第4列内容300', '第5列内容300');
INSERT INTO `plugin_example_test_data`
VALUES (301, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 401, 'yesNo_no', 'onOff_on', '',
        '第1列内容301', '第2列内容301', '第3列内容301', '第4列内容301', '第5列内容301');
INSERT INTO `plugin_example_test_data`
VALUES (302, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 402, 'yesNo_no', 'onOff_on', '',
        '第1列内容302', '第2列内容302', '第3列内容302', '第4列内容302', '第5列内容302');
INSERT INTO `plugin_example_test_data`
VALUES (303, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 403, 'yesNo_no', 'onOff_on', '',
        '第1列内容303', '第2列内容303', '第3列内容303', '第4列内容303', '第5列内容303');
INSERT INTO `plugin_example_test_data`
VALUES (304, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 404, 'yesNo_no', 'onOff_on', '',
        '第1列内容304', '第2列内容304', '第3列内容304', '第4列内容304', '第5列内容304');
INSERT INTO `plugin_example_test_data`
VALUES (305, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 405, 'yesNo_no', 'onOff_on', '',
        '第1列内容305', '第2列内容305', '第3列内容305', '第4列内容305', '第5列内容305');
INSERT INTO `plugin_example_test_data`
VALUES (306, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 406, 'yesNo_no', 'onOff_on', '',
        '第1列内容306', '第2列内容306', '第3列内容306', '第4列内容306', '第5列内容306');
INSERT INTO `plugin_example_test_data`
VALUES (307, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 407, 'yesNo_no', 'onOff_on', '',
        '第1列内容307', '第2列内容307', '第3列内容307', '第4列内容307', '第5列内容307');
INSERT INTO `plugin_example_test_data`
VALUES (308, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 408, 'yesNo_no', 'onOff_on', '',
        '第1列内容308', '第2列内容308', '第3列内容308', '第4列内容308', '第5列内容308');
INSERT INTO `plugin_example_test_data`
VALUES (309, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 409, 'yesNo_no', 'onOff_on', '',
        '第1列内容309', '第2列内容309', '第3列内容309', '第4列内容309', '第5列内容309');
INSERT INTO `plugin_example_test_data`
VALUES (310, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 410, 'yesNo_no', 'onOff_on', '',
        '第1列内容310', '第2列内容310', '第3列内容310', '第4列内容310', '第5列内容310');
INSERT INTO `plugin_example_test_data`
VALUES (311, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 411, 'yesNo_no', 'onOff_on', '',
        '第1列内容311', '第2列内容311', '第3列内容311', '第4列内容311', '第5列内容311');
INSERT INTO `plugin_example_test_data`
VALUES (312, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 412, 'yesNo_no', 'onOff_on', '',
        '第1列内容312', '第2列内容312', '第3列内容312', '第4列内容312', '第5列内容312');
INSERT INTO `plugin_example_test_data`
VALUES (313, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 413, 'yesNo_no', 'onOff_on', '',
        '第1列内容313', '第2列内容313', '第3列内容313', '第4列内容313', '第5列内容313');
INSERT INTO `plugin_example_test_data`
VALUES (314, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 414, 'yesNo_no', 'onOff_on', '',
        '第1列内容314', '第2列内容314', '第3列内容314', '第4列内容314', '第5列内容314');
INSERT INTO `plugin_example_test_data`
VALUES (315, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 415, 'yesNo_no', 'onOff_on', '',
        '第1列内容315', '第2列内容315', '第3列内容315', '第4列内容315', '第5列内容315');
INSERT INTO `plugin_example_test_data`
VALUES (316, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 416, 'yesNo_no', 'onOff_on', '',
        '第1列内容316', '第2列内容316', '第3列内容316', '第4列内容316', '第5列内容316');
INSERT INTO `plugin_example_test_data`
VALUES (317, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 417, 'yesNo_no', 'onOff_on', '',
        '第1列内容317', '第2列内容317', '第3列内容317', '第4列内容317', '第5列内容317');
INSERT INTO `plugin_example_test_data`
VALUES (318, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 418, 'yesNo_no', 'onOff_on', '',
        '第1列内容318', '第2列内容318', '第3列内容318', '第4列内容318', '第5列内容318');
INSERT INTO `plugin_example_test_data`
VALUES (319, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 419, 'yesNo_no', 'onOff_on', '',
        '第1列内容319', '第2列内容319', '第3列内容319', '第4列内容319', '第5列内容319');
INSERT INTO `plugin_example_test_data`
VALUES (320, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 420, 'yesNo_no', 'onOff_on', '',
        '第1列内容320', '第2列内容320', '第3列内容320', '第4列内容320', '第5列内容320');
INSERT INTO `plugin_example_test_data`
VALUES (321, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 421, 'yesNo_no', 'onOff_on', '',
        '第1列内容321', '第2列内容321', '第3列内容321', '第4列内容321', '第5列内容321');
INSERT INTO `plugin_example_test_data`
VALUES (322, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 422, 'yesNo_no', 'onOff_on', '',
        '第1列内容322', '第2列内容322', '第3列内容322', '第4列内容322', '第5列内容322');
INSERT INTO `plugin_example_test_data`
VALUES (323, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 423, 'yesNo_no', 'onOff_on', '',
        '第1列内容323', '第2列内容323', '第3列内容323', '第4列内容323', '第5列内容323');
INSERT INTO `plugin_example_test_data`
VALUES (324, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 424, 'yesNo_no', 'onOff_on', '',
        '第1列内容324', '第2列内容324', '第3列内容324', '第4列内容324', '第5列内容324');
INSERT INTO `plugin_example_test_data`
VALUES (325, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 425, 'yesNo_no', 'onOff_on', '',
        '第1列内容325', '第2列内容325', '第3列内容325', '第4列内容325', '第5列内容325');
INSERT INTO `plugin_example_test_data`
VALUES (326, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 426, 'yesNo_no', 'onOff_on', '',
        '第1列内容326', '第2列内容326', '第3列内容326', '第4列内容326', '第5列内容326');
INSERT INTO `plugin_example_test_data`
VALUES (327, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 427, 'yesNo_no', 'onOff_on', '',
        '第1列内容327', '第2列内容327', '第3列内容327', '第4列内容327', '第5列内容327');
INSERT INTO `plugin_example_test_data`
VALUES (328, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 428, 'yesNo_no', 'onOff_on', '',
        '第1列内容328', '第2列内容328', '第3列内容328', '第4列内容328', '第5列内容328');
INSERT INTO `plugin_example_test_data`
VALUES (329, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 429, 'yesNo_no', 'onOff_on', '',
        '第1列内容329', '第2列内容329', '第3列内容329', '第4列内容329', '第5列内容329');
INSERT INTO `plugin_example_test_data`
VALUES (330, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 430, 'yesNo_no', 'onOff_on', '',
        '第1列内容330', '第2列内容330', '第3列内容330', '第4列内容330', '第5列内容330');
INSERT INTO `plugin_example_test_data`
VALUES (331, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 431, 'yesNo_no', 'onOff_on', '',
        '第1列内容331', '第2列内容331', '第3列内容331', '第4列内容331', '第5列内容331');
INSERT INTO `plugin_example_test_data`
VALUES (332, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 432, 'yesNo_no', 'onOff_on', '',
        '第1列内容332', '第2列内容332', '第3列内容332', '第4列内容332', '第5列内容332');
INSERT INTO `plugin_example_test_data`
VALUES (333, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 433, 'yesNo_no', 'onOff_on', '',
        '第1列内容333', '第2列内容333', '第3列内容333', '第4列内容333', '第5列内容333');
INSERT INTO `plugin_example_test_data`
VALUES (334, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 434, 'yesNo_no', 'onOff_on', '',
        '第1列内容334', '第2列内容334', '第3列内容334', '第4列内容334', '第5列内容334');
INSERT INTO `plugin_example_test_data`
VALUES (335, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 435, 'yesNo_no', 'onOff_on', '',
        '第1列内容335', '第2列内容335', '第3列内容335', '第4列内容335', '第5列内容335');
INSERT INTO `plugin_example_test_data`
VALUES (336, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 436, 'yesNo_no', 'onOff_on', '',
        '第1列内容336', '第2列内容336', '第3列内容336', '第4列内容336', '第5列内容336');
INSERT INTO `plugin_example_test_data`
VALUES (337, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 437, 'yesNo_no', 'onOff_on', '',
        '第1列内容337', '第2列内容337', '第3列内容337', '第4列内容337', '第5列内容337');
INSERT INTO `plugin_example_test_data`
VALUES (338, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 438, 'yesNo_no', 'onOff_on', '',
        '第1列内容338', '第2列内容338', '第3列内容338', '第4列内容338', '第5列内容338');
INSERT INTO `plugin_example_test_data`
VALUES (339, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 439, 'yesNo_no', 'onOff_on', '',
        '第1列内容339', '第2列内容339', '第3列内容339', '第4列内容339', '第5列内容339');
INSERT INTO `plugin_example_test_data`
VALUES (340, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 440, 'yesNo_no', 'onOff_on', '',
        '第1列内容340', '第2列内容340', '第3列内容340', '第4列内容340', '第5列内容340');
INSERT INTO `plugin_example_test_data`
VALUES (341, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 441, 'yesNo_no', 'onOff_on', '',
        '第1列内容341', '第2列内容341', '第3列内容341', '第4列内容341', '第5列内容341');
INSERT INTO `plugin_example_test_data`
VALUES (342, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 442, 'yesNo_no', 'onOff_on', '',
        '第1列内容342', '第2列内容342', '第3列内容342', '第4列内容342', '第5列内容342');
INSERT INTO `plugin_example_test_data`
VALUES (343, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 443, 'yesNo_no', 'onOff_on', '',
        '第1列内容343', '第2列内容343', '第3列内容343', '第4列内容343', '第5列内容343');
INSERT INTO `plugin_example_test_data`
VALUES (344, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 444, 'yesNo_no', 'onOff_on', '',
        '第1列内容344', '第2列内容344', '第3列内容344', '第4列内容344', '第5列内容344');
INSERT INTO `plugin_example_test_data`
VALUES (345, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 445, 'yesNo_no', 'onOff_on', '',
        '第1列内容345', '第2列内容345', '第3列内容345', '第4列内容345', '第5列内容345');
INSERT INTO `plugin_example_test_data`
VALUES (346, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 446, 'yesNo_no', 'onOff_on', '',
        '第1列内容346', '第2列内容346', '第3列内容346', '第4列内容346', '第5列内容346');
INSERT INTO `plugin_example_test_data`
VALUES (347, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 447, 'yesNo_no', 'onOff_on', '',
        '第1列内容347', '第2列内容347', '第3列内容347', '第4列内容347', '第5列内容347');
INSERT INTO `plugin_example_test_data`
VALUES (348, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 448, 'yesNo_no', 'onOff_on', '',
        '第1列内容348', '第2列内容348', '第3列内容348', '第4列内容348', '第5列内容348');
INSERT INTO `plugin_example_test_data`
VALUES (349, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 449, 'yesNo_no', 'onOff_on', '',
        '第1列内容349', '第2列内容349', '第3列内容349', '第4列内容349', '第5列内容349');
INSERT INTO `plugin_example_test_data`
VALUES (350, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 450, 'yesNo_no', 'onOff_on', '',
        '第1列内容350', '第2列内容350', '第3列内容350', '第4列内容350', '第5列内容350');
INSERT INTO `plugin_example_test_data`
VALUES (351, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 451, 'yesNo_no', 'onOff_on', '',
        '第1列内容351', '第2列内容351', '第3列内容351', '第4列内容351', '第5列内容351');
INSERT INTO `plugin_example_test_data`
VALUES (352, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 452, 'yesNo_no', 'onOff_on', '',
        '第1列内容352', '第2列内容352', '第3列内容352', '第4列内容352', '第5列内容352');
INSERT INTO `plugin_example_test_data`
VALUES (353, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 453, 'yesNo_no', 'onOff_on', '',
        '第1列内容353', '第2列内容353', '第3列内容353', '第4列内容353', '第5列内容353');
INSERT INTO `plugin_example_test_data`
VALUES (354, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 454, 'yesNo_no', 'onOff_on', '',
        '第1列内容354', '第2列内容354', '第3列内容354', '第4列内容354', '第5列内容354');
INSERT INTO `plugin_example_test_data`
VALUES (355, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 455, 'yesNo_no', 'onOff_on', '',
        '第1列内容355', '第2列内容355', '第3列内容355', '第4列内容355', '第5列内容355');
INSERT INTO `plugin_example_test_data`
VALUES (356, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 456, 'yesNo_no', 'onOff_on', '',
        '第1列内容356', '第2列内容356', '第3列内容356', '第4列内容356', '第5列内容356');
INSERT INTO `plugin_example_test_data`
VALUES (357, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 457, 'yesNo_no', 'onOff_on', '',
        '第1列内容357', '第2列内容357', '第3列内容357', '第4列内容357', '第5列内容357');
INSERT INTO `plugin_example_test_data`
VALUES (358, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 458, 'yesNo_no', 'onOff_on', '',
        '第1列内容358', '第2列内容358', '第3列内容358', '第4列内容358', '第5列内容358');
INSERT INTO `plugin_example_test_data`
VALUES (359, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 459, 'yesNo_no', 'onOff_on', '',
        '第1列内容359', '第2列内容359', '第3列内容359', '第4列内容359', '第5列内容359');
INSERT INTO `plugin_example_test_data`
VALUES (360, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 460, 'yesNo_no', 'onOff_on', '',
        '第1列内容360', '第2列内容360', '第3列内容360', '第4列内容360', '第5列内容360');
INSERT INTO `plugin_example_test_data`
VALUES (361, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 461, 'yesNo_no', 'onOff_on', '',
        '第1列内容361', '第2列内容361', '第3列内容361', '第4列内容361', '第5列内容361');
INSERT INTO `plugin_example_test_data`
VALUES (362, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 462, 'yesNo_no', 'onOff_on', '',
        '第1列内容362', '第2列内容362', '第3列内容362', '第4列内容362', '第5列内容362');
INSERT INTO `plugin_example_test_data`
VALUES (363, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 463, 'yesNo_no', 'onOff_on', '',
        '第1列内容363', '第2列内容363', '第3列内容363', '第4列内容363', '第5列内容363');
INSERT INTO `plugin_example_test_data`
VALUES (364, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 464, 'yesNo_no', 'onOff_on', '',
        '第1列内容364', '第2列内容364', '第3列内容364', '第4列内容364', '第5列内容364');
INSERT INTO `plugin_example_test_data`
VALUES (365, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 465, 'yesNo_no', 'onOff_on', '',
        '第1列内容365', '第2列内容365', '第3列内容365', '第4列内容365', '第5列内容365');
INSERT INTO `plugin_example_test_data`
VALUES (366, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 466, 'yesNo_no', 'onOff_on', '',
        '第1列内容366', '第2列内容366', '第3列内容366', '第4列内容366', '第5列内容366');
INSERT INTO `plugin_example_test_data`
VALUES (367, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 467, 'yesNo_no', 'onOff_on', '',
        '第1列内容367', '第2列内容367', '第3列内容367', '第4列内容367', '第5列内容367');
INSERT INTO `plugin_example_test_data`
VALUES (368, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 468, 'yesNo_no', 'onOff_on', '',
        '第1列内容368', '第2列内容368', '第3列内容368', '第4列内容368', '第5列内容368');
INSERT INTO `plugin_example_test_data`
VALUES (369, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 469, 'yesNo_no', 'onOff_on', '',
        '第1列内容369', '第2列内容369', '第3列内容369', '第4列内容369', '第5列内容369');
INSERT INTO `plugin_example_test_data`
VALUES (370, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 470, 'yesNo_no', 'onOff_on', '',
        '第1列内容370', '第2列内容370', '第3列内容370', '第4列内容370', '第5列内容370');
INSERT INTO `plugin_example_test_data`
VALUES (371, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 471, 'yesNo_no', 'onOff_on', '',
        '第1列内容371', '第2列内容371', '第3列内容371', '第4列内容371', '第5列内容371');
INSERT INTO `plugin_example_test_data`
VALUES (372, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 472, 'yesNo_no', 'onOff_on', '',
        '第1列内容372', '第2列内容372', '第3列内容372', '第4列内容372', '第5列内容372');
INSERT INTO `plugin_example_test_data`
VALUES (373, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 473, 'yesNo_no', 'onOff_on', '',
        '第1列内容373', '第2列内容373', '第3列内容373', '第4列内容373', '第5列内容373');
INSERT INTO `plugin_example_test_data`
VALUES (374, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 474, 'yesNo_no', 'onOff_on', '',
        '第1列内容374', '第2列内容374', '第3列内容374', '第4列内容374', '第5列内容374');
INSERT INTO `plugin_example_test_data`
VALUES (375, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 475, 'yesNo_no', 'onOff_on', '',
        '第1列内容375', '第2列内容375', '第3列内容375', '第4列内容375', '第5列内容375');
INSERT INTO `plugin_example_test_data`
VALUES (376, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 476, 'yesNo_no', 'onOff_on', '',
        '第1列内容376', '第2列内容376', '第3列内容376', '第4列内容376', '第5列内容376');
INSERT INTO `plugin_example_test_data`
VALUES (377, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 477, 'yesNo_no', 'onOff_on', '',
        '第1列内容377', '第2列内容377', '第3列内容377', '第4列内容377', '第5列内容377');
INSERT INTO `plugin_example_test_data`
VALUES (378, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 478, 'yesNo_no', 'onOff_on', '',
        '第1列内容378', '第2列内容378', '第3列内容378', '第4列内容378', '第5列内容378');
INSERT INTO `plugin_example_test_data`
VALUES (379, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 479, 'yesNo_no', 'onOff_on', '',
        '第1列内容379', '第2列内容379', '第3列内容379', '第4列内容379', '第5列内容379');
INSERT INTO `plugin_example_test_data`
VALUES (380, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 480, 'yesNo_no', 'onOff_on', '',
        '第1列内容380', '第2列内容380', '第3列内容380', '第4列内容380', '第5列内容380');
INSERT INTO `plugin_example_test_data`
VALUES (381, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 481, 'yesNo_no', 'onOff_on', '',
        '第1列内容381', '第2列内容381', '第3列内容381', '第4列内容381', '第5列内容381');
INSERT INTO `plugin_example_test_data`
VALUES (382, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 482, 'yesNo_no', 'onOff_on', '',
        '第1列内容382', '第2列内容382', '第3列内容382', '第4列内容382', '第5列内容382');
INSERT INTO `plugin_example_test_data`
VALUES (383, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 483, 'yesNo_no', 'onOff_on', '',
        '第1列内容383', '第2列内容383', '第3列内容383', '第4列内容383', '第5列内容383');
INSERT INTO `plugin_example_test_data`
VALUES (384, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 484, 'yesNo_no', 'onOff_on', '',
        '第1列内容384', '第2列内容384', '第3列内容384', '第4列内容384', '第5列内容384');
INSERT INTO `plugin_example_test_data`
VALUES (385, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 485, 'yesNo_no', 'onOff_on', '',
        '第1列内容385', '第2列内容385', '第3列内容385', '第4列内容385', '第5列内容385');
INSERT INTO `plugin_example_test_data`
VALUES (386, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 486, 'yesNo_no', 'onOff_on', '',
        '第1列内容386', '第2列内容386', '第3列内容386', '第4列内容386', '第5列内容386');
INSERT INTO `plugin_example_test_data`
VALUES (387, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 487, 'yesNo_no', 'onOff_on', '',
        '第1列内容387', '第2列内容387', '第3列内容387', '第4列内容387', '第5列内容387');
INSERT INTO `plugin_example_test_data`
VALUES (388, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 488, 'yesNo_no', 'onOff_on', '',
        '第1列内容388', '第2列内容388', '第3列内容388', '第4列内容388', '第5列内容388');
INSERT INTO `plugin_example_test_data`
VALUES (389, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 489, 'yesNo_no', 'onOff_on', '',
        '第1列内容389', '第2列内容389', '第3列内容389', '第4列内容389', '第5列内容389');
INSERT INTO `plugin_example_test_data`
VALUES (390, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 490, 'yesNo_no', 'onOff_on', '',
        '第1列内容390', '第2列内容390', '第3列内容390', '第4列内容390', '第5列内容390');
INSERT INTO `plugin_example_test_data`
VALUES (391, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 491, 'yesNo_no', 'onOff_on', '',
        '第1列内容391', '第2列内容391', '第3列内容391', '第4列内容391', '第5列内容391');
INSERT INTO `plugin_example_test_data`
VALUES (392, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 492, 'yesNo_no', 'onOff_on', '',
        '第1列内容392', '第2列内容392', '第3列内容392', '第4列内容392', '第5列内容392');
INSERT INTO `plugin_example_test_data`
VALUES (393, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 493, 'yesNo_no', 'onOff_on', '',
        '第1列内容393', '第2列内容393', '第3列内容393', '第4列内容393', '第5列内容393');
INSERT INTO `plugin_example_test_data`
VALUES (394, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 494, 'yesNo_no', 'onOff_on', '',
        '第1列内容394', '第2列内容394', '第3列内容394', '第4列内容394', '第5列内容394');
INSERT INTO `plugin_example_test_data`
VALUES (395, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 495, 'yesNo_no', 'onOff_on', '',
        '第1列内容395', '第2列内容395', '第3列内容395', '第4列内容395', '第5列内容395');
INSERT INTO `plugin_example_test_data`
VALUES (396, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 496, 'yesNo_no', 'onOff_on', '',
        '第1列内容396', '第2列内容396', '第3列内容396', '第4列内容396', '第5列内容396');
INSERT INTO `plugin_example_test_data`
VALUES (397, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 497, 'yesNo_no', 'onOff_on', '',
        '第1列内容397', '第2列内容397', '第3列内容397', '第4列内容397', '第5列内容397');
INSERT INTO `plugin_example_test_data`
VALUES (398, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 498, 'yesNo_no', 'onOff_on', '',
        '第1列内容398', '第2列内容398', '第3列内容398', '第4列内容398', '第5列内容398');
INSERT INTO `plugin_example_test_data`
VALUES (399, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 499, 'yesNo_no', 'onOff_on', '',
        '第1列内容399', '第2列内容399', '第3列内容399', '第4列内容399', '第5列内容399');
INSERT INTO `plugin_example_test_data`
VALUES (400, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 500, 'yesNo_no', 'onOff_on', '',
        '第1列内容400', '第2列内容400', '第3列内容400', '第4列内容400', '第5列内容400');
INSERT INTO `plugin_example_test_data`
VALUES (401, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 501, 'yesNo_no', 'onOff_on', '',
        '第1列内容401', '第2列内容401', '第3列内容401', '第4列内容401', '第5列内容401');
INSERT INTO `plugin_example_test_data`
VALUES (402, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 502, 'yesNo_no', 'onOff_on', '',
        '第1列内容402', '第2列内容402', '第3列内容402', '第4列内容402', '第5列内容402');
INSERT INTO `plugin_example_test_data`
VALUES (403, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 503, 'yesNo_no', 'onOff_on', '',
        '第1列内容403', '第2列内容403', '第3列内容403', '第4列内容403', '第5列内容403');
INSERT INTO `plugin_example_test_data`
VALUES (404, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 504, 'yesNo_no', 'onOff_on', '',
        '第1列内容404', '第2列内容404', '第3列内容404', '第4列内容404', '第5列内容404');
INSERT INTO `plugin_example_test_data`
VALUES (405, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 505, 'yesNo_no', 'onOff_on', '',
        '第1列内容405', '第2列内容405', '第3列内容405', '第4列内容405', '第5列内容405');
INSERT INTO `plugin_example_test_data`
VALUES (406, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 506, 'yesNo_no', 'onOff_on', '',
        '第1列内容406', '第2列内容406', '第3列内容406', '第4列内容406', '第5列内容406');
INSERT INTO `plugin_example_test_data`
VALUES (407, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 507, 'yesNo_no', 'onOff_on', '',
        '第1列内容407', '第2列内容407', '第3列内容407', '第4列内容407', '第5列内容407');
INSERT INTO `plugin_example_test_data`
VALUES (408, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 508, 'yesNo_no', 'onOff_on', '',
        '第1列内容408', '第2列内容408', '第3列内容408', '第4列内容408', '第5列内容408');
INSERT INTO `plugin_example_test_data`
VALUES (409, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 509, 'yesNo_no', 'onOff_on', '',
        '第1列内容409', '第2列内容409', '第3列内容409', '第4列内容409', '第5列内容409');
INSERT INTO `plugin_example_test_data`
VALUES (410, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 510, 'yesNo_no', 'onOff_on', '',
        '第1列内容410', '第2列内容410', '第3列内容410', '第4列内容410', '第5列内容410');
INSERT INTO `plugin_example_test_data`
VALUES (411, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 511, 'yesNo_no', 'onOff_on', '',
        '第1列内容411', '第2列内容411', '第3列内容411', '第4列内容411', '第5列内容411');
INSERT INTO `plugin_example_test_data`
VALUES (412, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 512, 'yesNo_no', 'onOff_on', '',
        '第1列内容412', '第2列内容412', '第3列内容412', '第4列内容412', '第5列内容412');
INSERT INTO `plugin_example_test_data`
VALUES (413, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 513, 'yesNo_no', 'onOff_on', '',
        '第1列内容413', '第2列内容413', '第3列内容413', '第4列内容413', '第5列内容413');
INSERT INTO `plugin_example_test_data`
VALUES (414, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 514, 'yesNo_no', 'onOff_on', '',
        '第1列内容414', '第2列内容414', '第3列内容414', '第4列内容414', '第5列内容414');
INSERT INTO `plugin_example_test_data`
VALUES (415, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 515, 'yesNo_no', 'onOff_on', '',
        '第1列内容415', '第2列内容415', '第3列内容415', '第4列内容415', '第5列内容415');
INSERT INTO `plugin_example_test_data`
VALUES (416, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 516, 'yesNo_no', 'onOff_on', '',
        '第1列内容416', '第2列内容416', '第3列内容416', '第4列内容416', '第5列内容416');
INSERT INTO `plugin_example_test_data`
VALUES (417, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 517, 'yesNo_no', 'onOff_on', '',
        '第1列内容417', '第2列内容417', '第3列内容417', '第4列内容417', '第5列内容417');
INSERT INTO `plugin_example_test_data`
VALUES (418, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 518, 'yesNo_no', 'onOff_on', '',
        '第1列内容418', '第2列内容418', '第3列内容418', '第4列内容418', '第5列内容418');
INSERT INTO `plugin_example_test_data`
VALUES (419, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 519, 'yesNo_no', 'onOff_on', '',
        '第1列内容419', '第2列内容419', '第3列内容419', '第4列内容419', '第5列内容419');
INSERT INTO `plugin_example_test_data`
VALUES (420, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 520, 'yesNo_no', 'onOff_on', '',
        '第1列内容420', '第2列内容420', '第3列内容420', '第4列内容420', '第5列内容420');
INSERT INTO `plugin_example_test_data`
VALUES (421, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 521, 'yesNo_no', 'onOff_on', '',
        '第1列内容421', '第2列内容421', '第3列内容421', '第4列内容421', '第5列内容421');
INSERT INTO `plugin_example_test_data`
VALUES (422, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 522, 'yesNo_no', 'onOff_on', '',
        '第1列内容422', '第2列内容422', '第3列内容422', '第4列内容422', '第5列内容422');
INSERT INTO `plugin_example_test_data`
VALUES (423, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 523, 'yesNo_no', 'onOff_on', '',
        '第1列内容423', '第2列内容423', '第3列内容423', '第4列内容423', '第5列内容423');
INSERT INTO `plugin_example_test_data`
VALUES (424, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 524, 'yesNo_no', 'onOff_on', '',
        '第1列内容424', '第2列内容424', '第3列内容424', '第4列内容424', '第5列内容424');
INSERT INTO `plugin_example_test_data`
VALUES (425, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 525, 'yesNo_no', 'onOff_on', '',
        '第1列内容425', '第2列内容425', '第3列内容425', '第4列内容425', '第5列内容425');
INSERT INTO `plugin_example_test_data`
VALUES (426, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 526, 'yesNo_no', 'onOff_on', '',
        '第1列内容426', '第2列内容426', '第3列内容426', '第4列内容426', '第5列内容426');
INSERT INTO `plugin_example_test_data`
VALUES (427, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 527, 'yesNo_no', 'onOff_on', '',
        '第1列内容427', '第2列内容427', '第3列内容427', '第4列内容427', '第5列内容427');
INSERT INTO `plugin_example_test_data`
VALUES (428, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 528, 'yesNo_no', 'onOff_on', '',
        '第1列内容428', '第2列内容428', '第3列内容428', '第4列内容428', '第5列内容428');
INSERT INTO `plugin_example_test_data`
VALUES (429, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 529, 'yesNo_no', 'onOff_on', '',
        '第1列内容429', '第2列内容429', '第3列内容429', '第4列内容429', '第5列内容429');
INSERT INTO `plugin_example_test_data`
VALUES (430, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 530, 'yesNo_no', 'onOff_on', '',
        '第1列内容430', '第2列内容430', '第3列内容430', '第4列内容430', '第5列内容430');
INSERT INTO `plugin_example_test_data`
VALUES (431, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 531, 'yesNo_no', 'onOff_on', '',
        '第1列内容431', '第2列内容431', '第3列内容431', '第4列内容431', '第5列内容431');
INSERT INTO `plugin_example_test_data`
VALUES (432, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 532, 'yesNo_no', 'onOff_on', '',
        '第1列内容432', '第2列内容432', '第3列内容432', '第4列内容432', '第5列内容432');
INSERT INTO `plugin_example_test_data`
VALUES (433, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 533, 'yesNo_no', 'onOff_on', '',
        '第1列内容433', '第2列内容433', '第3列内容433', '第4列内容433', '第5列内容433');
INSERT INTO `plugin_example_test_data`
VALUES (434, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 534, 'yesNo_no', 'onOff_on', '',
        '第1列内容434', '第2列内容434', '第3列内容434', '第4列内容434', '第5列内容434');
INSERT INTO `plugin_example_test_data`
VALUES (435, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 535, 'yesNo_no', 'onOff_on', '',
        '第1列内容435', '第2列内容435', '第3列内容435', '第4列内容435', '第5列内容435');
INSERT INTO `plugin_example_test_data`
VALUES (436, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 536, 'yesNo_no', 'onOff_on', '',
        '第1列内容436', '第2列内容436', '第3列内容436', '第4列内容436', '第5列内容436');
INSERT INTO `plugin_example_test_data`
VALUES (437, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 537, 'yesNo_no', 'onOff_on', '',
        '第1列内容437', '第2列内容437', '第3列内容437', '第4列内容437', '第5列内容437');
INSERT INTO `plugin_example_test_data`
VALUES (438, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 538, 'yesNo_no', 'onOff_on', '',
        '第1列内容438', '第2列内容438', '第3列内容438', '第4列内容438', '第5列内容438');
INSERT INTO `plugin_example_test_data`
VALUES (439, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 539, 'yesNo_no', 'onOff_on', '',
        '第1列内容439', '第2列内容439', '第3列内容439', '第4列内容439', '第5列内容439');
INSERT INTO `plugin_example_test_data`
VALUES (440, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 540, 'yesNo_no', 'onOff_on', '',
        '第1列内容440', '第2列内容440', '第3列内容440', '第4列内容440', '第5列内容440');
INSERT INTO `plugin_example_test_data`
VALUES (441, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 541, 'yesNo_no', 'onOff_on', '',
        '第1列内容441', '第2列内容441', '第3列内容441', '第4列内容441', '第5列内容441');
INSERT INTO `plugin_example_test_data`
VALUES (442, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 542, 'yesNo_no', 'onOff_on', '',
        '第1列内容442', '第2列内容442', '第3列内容442', '第4列内容442', '第5列内容442');
INSERT INTO `plugin_example_test_data`
VALUES (443, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 543, 'yesNo_no', 'onOff_on', '',
        '第1列内容443', '第2列内容443', '第3列内容443', '第4列内容443', '第5列内容443');
INSERT INTO `plugin_example_test_data`
VALUES (444, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 544, 'yesNo_no', 'onOff_on', '',
        '第1列内容444', '第2列内容444', '第3列内容444', '第4列内容444', '第5列内容444');
INSERT INTO `plugin_example_test_data`
VALUES (445, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 545, 'yesNo_no', 'onOff_on', '',
        '第1列内容445', '第2列内容445', '第3列内容445', '第4列内容445', '第5列内容445');
INSERT INTO `plugin_example_test_data`
VALUES (446, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 546, 'yesNo_no', 'onOff_on', '',
        '第1列内容446', '第2列内容446', '第3列内容446', '第4列内容446', '第5列内容446');
INSERT INTO `plugin_example_test_data`
VALUES (447, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 547, 'yesNo_no', 'onOff_on', '',
        '第1列内容447', '第2列内容447', '第3列内容447', '第4列内容447', '第5列内容447');
INSERT INTO `plugin_example_test_data`
VALUES (448, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 548, 'yesNo_no', 'onOff_on', '',
        '第1列内容448', '第2列内容448', '第3列内容448', '第4列内容448', '第5列内容448');
INSERT INTO `plugin_example_test_data`
VALUES (449, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 549, 'yesNo_no', 'onOff_on', '',
        '第1列内容449', '第2列内容449', '第3列内容449', '第4列内容449', '第5列内容449');
INSERT INTO `plugin_example_test_data`
VALUES (450, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 550, 'yesNo_no', 'onOff_on', '',
        '第1列内容450', '第2列内容450', '第3列内容450', '第4列内容450', '第5列内容450');
INSERT INTO `plugin_example_test_data`
VALUES (451, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 551, 'yesNo_no', 'onOff_on', '',
        '第1列内容451', '第2列内容451', '第3列内容451', '第4列内容451', '第5列内容451');
INSERT INTO `plugin_example_test_data`
VALUES (452, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 552, 'yesNo_no', 'onOff_on', '',
        '第1列内容452', '第2列内容452', '第3列内容452', '第4列内容452', '第5列内容452');
INSERT INTO `plugin_example_test_data`
VALUES (453, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 553, 'yesNo_no', 'onOff_on', '',
        '第1列内容453', '第2列内容453', '第3列内容453', '第4列内容453', '第5列内容453');
INSERT INTO `plugin_example_test_data`
VALUES (454, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 554, 'yesNo_no', 'onOff_on', '',
        '第1列内容454', '第2列内容454', '第3列内容454', '第4列内容454', '第5列内容454');
INSERT INTO `plugin_example_test_data`
VALUES (455, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 555, 'yesNo_no', 'onOff_on', '',
        '第1列内容455', '第2列内容455', '第3列内容455', '第4列内容455', '第5列内容455');
INSERT INTO `plugin_example_test_data`
VALUES (456, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 556, 'yesNo_no', 'onOff_on', '',
        '第1列内容456', '第2列内容456', '第3列内容456', '第4列内容456', '第5列内容456');
INSERT INTO `plugin_example_test_data`
VALUES (457, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 557, 'yesNo_no', 'onOff_on', '',
        '第1列内容457', '第2列内容457', '第3列内容457', '第4列内容457', '第5列内容457');
INSERT INTO `plugin_example_test_data`
VALUES (458, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 558, 'yesNo_no', 'onOff_on', '',
        '第1列内容458', '第2列内容458', '第3列内容458', '第4列内容458', '第5列内容458');
INSERT INTO `plugin_example_test_data`
VALUES (459, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 559, 'yesNo_no', 'onOff_on', '',
        '第1列内容459', '第2列内容459', '第3列内容459', '第4列内容459', '第5列内容459');
INSERT INTO `plugin_example_test_data`
VALUES (460, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 560, 'yesNo_no', 'onOff_on', '',
        '第1列内容460', '第2列内容460', '第3列内容460', '第4列内容460', '第5列内容460');
INSERT INTO `plugin_example_test_data`
VALUES (461, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 561, 'yesNo_no', 'onOff_on', '',
        '第1列内容461', '第2列内容461', '第3列内容461', '第4列内容461', '第5列内容461');
INSERT INTO `plugin_example_test_data`
VALUES (462, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 562, 'yesNo_no', 'onOff_on', '',
        '第1列内容462', '第2列内容462', '第3列内容462', '第4列内容462', '第5列内容462');
INSERT INTO `plugin_example_test_data`
VALUES (463, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 563, 'yesNo_no', 'onOff_on', '',
        '第1列内容463', '第2列内容463', '第3列内容463', '第4列内容463', '第5列内容463');
INSERT INTO `plugin_example_test_data`
VALUES (464, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 564, 'yesNo_no', 'onOff_on', '',
        '第1列内容464', '第2列内容464', '第3列内容464', '第4列内容464', '第5列内容464');
INSERT INTO `plugin_example_test_data`
VALUES (465, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 565, 'yesNo_no', 'onOff_on', '',
        '第1列内容465', '第2列内容465', '第3列内容465', '第4列内容465', '第5列内容465');
INSERT INTO `plugin_example_test_data`
VALUES (466, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 566, 'yesNo_no', 'onOff_on', '',
        '第1列内容466', '第2列内容466', '第3列内容466', '第4列内容466', '第5列内容466');
INSERT INTO `plugin_example_test_data`
VALUES (467, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 567, 'yesNo_no', 'onOff_on', '',
        '第1列内容467', '第2列内容467', '第3列内容467', '第4列内容467', '第5列内容467');
INSERT INTO `plugin_example_test_data`
VALUES (468, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 568, 'yesNo_no', 'onOff_on', '',
        '第1列内容468', '第2列内容468', '第3列内容468', '第4列内容468', '第5列内容468');
INSERT INTO `plugin_example_test_data`
VALUES (469, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 569, 'yesNo_no', 'onOff_on', '',
        '第1列内容469', '第2列内容469', '第3列内容469', '第4列内容469', '第5列内容469');
INSERT INTO `plugin_example_test_data`
VALUES (470, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 570, 'yesNo_no', 'onOff_on', '',
        '第1列内容470', '第2列内容470', '第3列内容470', '第4列内容470', '第5列内容470');
INSERT INTO `plugin_example_test_data`
VALUES (471, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 571, 'yesNo_no', 'onOff_on', '',
        '第1列内容471', '第2列内容471', '第3列内容471', '第4列内容471', '第5列内容471');
INSERT INTO `plugin_example_test_data`
VALUES (472, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 572, 'yesNo_no', 'onOff_on', '',
        '第1列内容472', '第2列内容472', '第3列内容472', '第4列内容472', '第5列内容472');
INSERT INTO `plugin_example_test_data`
VALUES (473, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 573, 'yesNo_no', 'onOff_on', '',
        '第1列内容473', '第2列内容473', '第3列内容473', '第4列内容473', '第5列内容473');
INSERT INTO `plugin_example_test_data`
VALUES (474, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 574, 'yesNo_no', 'onOff_on', '',
        '第1列内容474', '第2列内容474', '第3列内容474', '第4列内容474', '第5列内容474');
INSERT INTO `plugin_example_test_data`
VALUES (475, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 575, 'yesNo_no', 'onOff_on', '',
        '第1列内容475', '第2列内容475', '第3列内容475', '第4列内容475', '第5列内容475');
INSERT INTO `plugin_example_test_data`
VALUES (476, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 576, 'yesNo_no', 'onOff_on', '',
        '第1列内容476', '第2列内容476', '第3列内容476', '第4列内容476', '第5列内容476');
INSERT INTO `plugin_example_test_data`
VALUES (477, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 577, 'yesNo_no', 'onOff_on', '',
        '第1列内容477', '第2列内容477', '第3列内容477', '第4列内容477', '第5列内容477');
INSERT INTO `plugin_example_test_data`
VALUES (478, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 578, 'yesNo_no', 'onOff_on', '',
        '第1列内容478', '第2列内容478', '第3列内容478', '第4列内容478', '第5列内容478');
INSERT INTO `plugin_example_test_data`
VALUES (479, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 579, 'yesNo_no', 'onOff_on', '',
        '第1列内容479', '第2列内容479', '第3列内容479', '第4列内容479', '第5列内容479');
INSERT INTO `plugin_example_test_data`
VALUES (480, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 580, 'yesNo_no', 'onOff_on', '',
        '第1列内容480', '第2列内容480', '第3列内容480', '第4列内容480', '第5列内容480');
INSERT INTO `plugin_example_test_data`
VALUES (481, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 581, 'yesNo_no', 'onOff_on', '',
        '第1列内容481', '第2列内容481', '第3列内容481', '第4列内容481', '第5列内容481');
INSERT INTO `plugin_example_test_data`
VALUES (482, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 582, 'yesNo_no', 'onOff_on', '',
        '第1列内容482', '第2列内容482', '第3列内容482', '第4列内容482', '第5列内容482');
INSERT INTO `plugin_example_test_data`
VALUES (483, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 583, 'yesNo_no', 'onOff_on', '',
        '第1列内容483', '第2列内容483', '第3列内容483', '第4列内容483', '第5列内容483');
INSERT INTO `plugin_example_test_data`
VALUES (484, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 584, 'yesNo_no', 'onOff_on', '',
        '第1列内容484', '第2列内容484', '第3列内容484', '第4列内容484', '第5列内容484');
INSERT INTO `plugin_example_test_data`
VALUES (485, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 585, 'yesNo_no', 'onOff_on', '',
        '第1列内容485', '第2列内容485', '第3列内容485', '第4列内容485', '第5列内容485');
INSERT INTO `plugin_example_test_data`
VALUES (486, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 586, 'yesNo_no', 'onOff_on', '',
        '第1列内容486', '第2列内容486', '第3列内容486', '第4列内容486', '第5列内容486');
INSERT INTO `plugin_example_test_data`
VALUES (487, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 587, 'yesNo_no', 'onOff_on', '',
        '第1列内容487', '第2列内容487', '第3列内容487', '第4列内容487', '第5列内容487');
INSERT INTO `plugin_example_test_data`
VALUES (488, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 588, 'yesNo_no', 'onOff_on', '',
        '第1列内容488', '第2列内容488', '第3列内容488', '第4列内容488', '第5列内容488');
INSERT INTO `plugin_example_test_data`
VALUES (489, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 589, 'yesNo_no', 'onOff_on', '',
        '第1列内容489', '第2列内容489', '第3列内容489', '第4列内容489', '第5列内容489');
INSERT INTO `plugin_example_test_data`
VALUES (490, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 590, 'yesNo_no', 'onOff_on', '',
        '第1列内容490', '第2列内容490', '第3列内容490', '第4列内容490', '第5列内容490');
INSERT INTO `plugin_example_test_data`
VALUES (491, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 591, 'yesNo_no', 'onOff_on', '',
        '第1列内容491', '第2列内容491', '第3列内容491', '第4列内容491', '第5列内容491');
INSERT INTO `plugin_example_test_data`
VALUES (492, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 592, 'yesNo_no', 'onOff_on', '',
        '第1列内容492', '第2列内容492', '第3列内容492', '第4列内容492', '第5列内容492');
INSERT INTO `plugin_example_test_data`
VALUES (493, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 593, 'yesNo_no', 'onOff_on', '',
        '第1列内容493', '第2列内容493', '第3列内容493', '第4列内容493', '第5列内容493');
INSERT INTO `plugin_example_test_data`
VALUES (494, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 594, 'yesNo_no', 'onOff_on', '',
        '第1列内容494', '第2列内容494', '第3列内容494', '第4列内容494', '第5列内容494');
INSERT INTO `plugin_example_test_data`
VALUES (495, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 595, 'yesNo_no', 'onOff_on', '',
        '第1列内容495', '第2列内容495', '第3列内容495', '第4列内容495', '第5列内容495');
INSERT INTO `plugin_example_test_data`
VALUES (496, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 596, 'yesNo_no', 'onOff_on', '',
        '第1列内容496', '第2列内容496', '第3列内容496', '第4列内容496', '第5列内容496');
INSERT INTO `plugin_example_test_data`
VALUES (497, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 597, 'yesNo_no', 'onOff_on', '',
        '第1列内容497', '第2列内容497', '第3列内容497', '第4列内容497', '第5列内容497');
INSERT INTO `plugin_example_test_data`
VALUES (498, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 598, 'yesNo_no', 'onOff_on', '',
        '第1列内容498', '第2列内容498', '第3列内容498', '第4列内容498', '第5列内容498');
INSERT INTO `plugin_example_test_data`
VALUES (499, '2023-12-04 13:02:58', '', '2023-12-04 13:02:58', '', NULL, 599, 'yesNo_no', 'onOff_on', '',
        '第1列内容499', '第2列内容499', '第3列内容499', '第4列内容499', '第5列内容499');

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api`
(
    `id`         bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at` datetime                                                      NULL     DEFAULT NULL,
    `created_by` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `updated_at` datetime                                                      NULL     DEFAULT NULL,
    `updated_by` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `deleted_at` datetime                                                      NULL     DEFAULT NULL,
    `sort`       bigint UNSIGNED                                               NULL     DEFAULT 1 COMMENT '排序',
    `stable`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT 'onOff_on' COMMENT '状态',
    `memo`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `api_group`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Api分组',
    `api_method` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'POST' COMMENT '请求方法',
    `api_path`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Api地址',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_api_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_api_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_sys_api_api_group` (`api_group` ASC) USING BTREE,
    INDEX `idx_sys_api_api_method` (`api_method` ASC) USING BTREE,
    INDEX `idx_sys_api_api_path` (`api_path` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 99
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_api
-- ----------------------------
INSERT INTO `sys_api`
VALUES (1, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on',
        '获取用户的菜单（必选）', 'user', 'POST', '/user/get-user-menu');
INSERT INTO `sys_api`
VALUES (2, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '获取用户列表',
        'user', 'POST', '/user/get-user-list');
INSERT INTO `sys_api`
VALUES (3, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 3, 'yesNo_yes', 'onOff_on', '编辑用户信息',
        'user', 'POST', '/user/edit-user');
INSERT INTO `sys_api`
VALUES (4, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 4, 'yesNo_yes', 'onOff_on', '新增用户',
        'user', 'POST', '/user/add-user');
INSERT INTO `sys_api`
VALUES (5, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 5, 'yesNo_yes', 'onOff_on', '删除用户',
        'user', 'POST', '/user/delete-user-by-id');
INSERT INTO `sys_api`
VALUES (6, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 6, 'yesNo_yes', 'onOff_on',
        '根据ID查找用户', 'user', 'POST', '/user/query-user-by-id');
INSERT INTO `sys_api`
VALUES (7, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 7, 'yesNo_yes', 'onOff_on', '重置用户密码',
        'user', 'POST', '/user/reset-password');
INSERT INTO `sys_api`
VALUES (8, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 8, 'yesNo_yes', 'onOff_on', '用户修改密码',
        'user', 'POST', '/user/change-password');
INSERT INTO `sys_api`
VALUES (9, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 9, 'yesNo_yes', 'onOff_on', '用户修改昵称',
        'user', 'POST', '/user/change-nickname');
INSERT INTO `sys_api`
VALUES (10, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 10, 'yesNo_yes', 'onOff_on',
        '获取角色列表', 'role', 'POST', '/role/get-role-list');
INSERT INTO `sys_api`
VALUES (11, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 11, 'yesNo_yes', 'onOff_on',
        '编辑角色信息', 'role', 'POST', '/role/edit-role');
INSERT INTO `sys_api`
VALUES (12, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 12, 'yesNo_yes', 'onOff_on', '新增角色',
        'role', 'POST', '/role/add-role');
INSERT INTO `sys_api`
VALUES (13, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 13, 'yesNo_yes', 'onOff_on', '删除角色',
        'role', 'POST', '/role/delete-role-by-id');
INSERT INTO `sys_api`
VALUES (14, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 14, 'yesNo_yes', 'onOff_on',
        '根据ID查找角色', 'role', 'POST', '/role/query-role-by-id');
INSERT INTO `sys_api`
VALUES (15, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 15, 'yesNo_yes', 'onOff_on',
        '获取角色菜单', 'role', 'POST', '/role/get-role-menu-list');
INSERT INTO `sys_api`
VALUES (16, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 16, 'yesNo_yes', 'onOff_on',
        '编辑角色菜单', 'role', 'POST', '/role/edit-role-menu');
INSERT INTO `sys_api`
VALUES (17, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 17, 'yesNo_yes', 'onOff_on', '获取角色API',
        'role', 'POST', '/role/get-role-api-list');
INSERT INTO `sys_api`
VALUES (18, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 18, 'yesNo_yes', 'onOff_on', '编辑角色Api',
        'role', 'POST', '/role/edit-role-api');
INSERT INTO `sys_api`
VALUES (19, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 19, 'yesNo_yes', 'onOff_on',
        '根据角色查找用户', 'role', 'POST', '/role/query-user-by-role');
INSERT INTO `sys_api`
VALUES (20, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 20, 'yesNo_yes', 'onOff_on',
        '从角色中移除某个用户', 'role', 'POST', '/role/remove-role-user');
INSERT INTO `sys_api`
VALUES (21, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 21, 'yesNo_yes', 'onOff_on',
        '添加用户到某个角色', 'role', 'POST', '/role/add-role-user');
INSERT INTO `sys_api`
VALUES (22, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 22, 'yesNo_yes', 'onOff_on',
        '修改角色部门数据权限', 'role', 'POST', '/role/edit-role-dept-data-permission');
INSERT INTO `sys_api`
VALUES (23, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 23, 'yesNo_yes', 'onOff_on',
        '获取角色按钮列表', 'role', 'POST', '/role/get-role-button-list');
INSERT INTO `sys_api`
VALUES (24, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 24, 'yesNo_yes', 'onOff_on',
        '获取菜单列表', 'menu', 'POST', '/menu/get-menu-list');
INSERT INTO `sys_api`
VALUES (25, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 25, 'yesNo_yes', 'onOff_on',
        '编辑菜单信息', 'menu', 'POST', '/menu/edit-menu');
INSERT INTO `sys_api`
VALUES (26, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 26, 'yesNo_yes', 'onOff_on', '新增菜单',
        'menu', 'POST', '/menu/add-menu');
INSERT INTO `sys_api`
VALUES (27, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 27, 'yesNo_yes', 'onOff_on', '删除菜单',
        'menu', 'POST', '/menu/delete-menu-by-id');
INSERT INTO `sys_api`
VALUES (28, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 28, 'yesNo_yes', 'onOff_on',
        '根据ID查找菜单', 'menu', 'POST', '/menu/query-menu-by-id');
INSERT INTO `sys_api`
VALUES (29, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 29, 'yesNo_yes', 'onOff_on',
        '获取部门列表', 'dept', 'POST', '/dept/get-dept-list');
INSERT INTO `sys_api`
VALUES (30, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 30, 'yesNo_yes', 'onOff_on',
        '编辑部门信息', 'dept', 'POST', '/dept/edit-dept');
INSERT INTO `sys_api`
VALUES (31, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 31, 'yesNo_yes', 'onOff_on', '新增部门',
        'dept', 'POST', '/dept/add-dept');
INSERT INTO `sys_api`
VALUES (32, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 32, 'yesNo_yes', 'onOff_on', '删除部门',
        'dept', 'POST', '/dept/delete-dept-by-id');
INSERT INTO `sys_api`
VALUES (33, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 33, 'yesNo_yes', 'onOff_on',
        '根据ID查找部门', 'dept', 'POST', '/dept/query-dept-by-id');
INSERT INTO `sys_api`
VALUES (34, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 34, 'yesNo_yes', 'onOff_on',
        '根据部门查找用户', 'dept', 'POST', '/dept/query-user-by-dept');
INSERT INTO `sys_api`
VALUES (35, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 35, 'yesNo_yes', 'onOff_on',
        '从部门中移除某个用户', 'dept', 'POST', '/dept/remove-dept-user');
INSERT INTO `sys_api`
VALUES (36, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 36, 'yesNo_yes', 'onOff_on',
        '添加用户到部门', 'dept', 'POST', '/dept/add-dept-user');
INSERT INTO `sys_api`
VALUES (37, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 37, 'yesNo_yes', 'onOff_on',
        '获取根字典列表', 'dict', 'POST', '/dict/get-dict-list');
INSERT INTO `sys_api`
VALUES (38, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 38, 'yesNo_yes', 'onOff_on',
        '编辑字典信息', 'dict', 'POST', '/dict/edit-dict');
INSERT INTO `sys_api`
VALUES (39, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 39, 'yesNo_yes', 'onOff_on', '新增字典',
        'dict', 'POST', '/dict/add-dict');
INSERT INTO `sys_api`
VALUES (40, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 40, 'yesNo_yes', 'onOff_on', '删除字典',
        'dict', 'POST', '/dict/delete-dict-by-id');
INSERT INTO `sys_api`
VALUES (41, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 41, 'yesNo_yes', 'onOff_on',
        '根据ID查找字典', 'dict', 'POST', '/dict/query-dict-by-id');
INSERT INTO `sys_api`
VALUES (42, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 42, 'yesNo_yes', 'onOff_on', '获取api列表',
        'api', 'POST', '/api/get-api-list');
INSERT INTO `sys_api`
VALUES (43, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 43, 'yesNo_yes', 'onOff_on', '编辑api信息',
        'api', 'POST', '/api/edit-api');
INSERT INTO `sys_api`
VALUES (44, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 44, 'yesNo_yes', 'onOff_on', '新增api',
        'api', 'POST', '/api/add-api');
INSERT INTO `sys_api`
VALUES (45, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 45, 'yesNo_yes', 'onOff_on',
        '根据id删除api', 'api', 'POST', '/api/delete-api-by-id');
INSERT INTO `sys_api`
VALUES (46, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 46, 'yesNo_yes', 'onOff_on',
        '根据id查找api', 'api', 'POST', '/api/query-api-by-id');
INSERT INTO `sys_api`
VALUES (47, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 47, 'yesNo_yes', 'onOff_on', '上传头像',
        'upload', 'POST', '/upload/upload-avatar');
INSERT INTO `sys_api`
VALUES (48, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 48, 'yesNo_yes', 'onOff_on', '上传文件',
        'upload', 'POST', '/upload/upload-file');
INSERT INTO `sys_api`
VALUES (49, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 49, 'yesNo_yes', 'onOff_on',
        '上传首页大图', 'upload', 'POST', '/upload/upload-banner-image');
INSERT INTO `sys_api`
VALUES (50, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 50, 'yesNo_yes', 'onOff_on',
        '上传网站Logo', 'upload', 'POST', '/upload/upload-logo');
INSERT INTO `sys_api`
VALUES (51, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 51, 'yesNo_yes', 'onOff_on',
        '上传标签页Logo', 'upload', 'POST', '/upload/upload-favicon');
INSERT INTO `sys_api`
VALUES (52, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 52, 'yesNo_yes', 'onOff_on',
        '获取后台配置列表', 'config-backend', 'POST', '/config-backend/get-config-backend-list');
INSERT INTO `sys_api`
VALUES (53, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 53, 'yesNo_yes', 'onOff_on',
        '编辑后台配置', 'config-backend', 'POST', '/config-backend/edit-config-backend');
INSERT INTO `sys_api`
VALUES (54, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 54, 'yesNo_yes', 'onOff_on',
        '新增后台配置', 'config-backend', 'POST', '/config-backend/add-config-backend');
INSERT INTO `sys_api`
VALUES (55, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 55, 'yesNo_yes', 'onOff_on',
        '删除后台配置', 'config-backend', 'POST', '/config-backend/delete-config-backend-by-id');
INSERT INTO `sys_api`
VALUES (56, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 56, 'yesNo_yes', 'onOff_on',
        '获取前台配置列表', 'config-frontend', 'POST', '/config-frontend/get-config-frontend-list');
INSERT INTO `sys_api`
VALUES (57, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 57, 'yesNo_yes', 'onOff_on',
        '编辑前台配置', 'config-frontend', 'POST', '/config-frontend/edit-config-frontend');
INSERT INTO `sys_api`
VALUES (58, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 58, 'yesNo_yes', 'onOff_on',
        '新增前台配置', 'config-frontend', 'POST', '/config-frontend/add-config-frontend');
INSERT INTO `sys_api`
VALUES (59, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 59, 'yesNo_yes', 'onOff_on',
        '删除前台配置', 'config-frontend', 'POST', '/config-frontend/delete-config-frontend-by-id');
INSERT INTO `sys_api`
VALUES (60, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 60, 'yesNo_yes', 'onOff_on',
        '获取登录日志列表', 'log', 'POST', '/log/get-log-login-list');
INSERT INTO `sys_api`
VALUES (61, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 61, 'yesNo_yes', 'onOff_on',
        '删除登录日志', 'log', 'POST', '/log/delete-log-login-by-id');
INSERT INTO `sys_api`
VALUES (62, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 62, 'yesNo_yes', 'onOff_on',
        '获取操作日志列表', 'log', 'POST', '/log/get-log-operation-list');
INSERT INTO `sys_api`
VALUES (63, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 63, 'yesNo_yes', 'onOff_on',
        '删除操作日志', 'log', 'POST', '/log/delete-log-operation-by-id');
INSERT INTO `sys_api`
VALUES (64, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 64, 'yesNo_yes', 'onOff_on',
        '获取消息列表', 'notice', 'POST', '/notice/get-notice-list');
INSERT INTO `sys_api`
VALUES (65, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 65, 'yesNo_yes', 'onOff_on', '新增消息',
        'notice', 'POST', '/notice/add-notice');
INSERT INTO `sys_api`
VALUES (66, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 66, 'yesNo_yes', 'onOff_on', '删除消息',
        'notice', 'POST', '/notice/delete-notice-by-id');
INSERT INTO `sys_api`
VALUES (67, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 67, 'yesNo_yes', 'onOff_on',
        '根据ID查找消息', 'notice', 'POST', '/notice/query-notice-by-id');
INSERT INTO `sys_api`
VALUES (68, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 68, 'yesNo_yes', 'onOff_on',
        '根据ID查找消息并阅读', 'notice', 'POST', '/notice/query-notice-read-by-id');
INSERT INTO `sys_api`
VALUES (69, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 69, 'yesNo_yes', 'onOff_on', '发送消息',
        'notice', 'POST', '/notice/send-notice');
INSERT INTO `sys_api`
VALUES (70, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 70, 'yesNo_yes', 'onOff_on',
        '获取待办列表', 'todo', 'POST', '/todo/get-todo-list');
INSERT INTO `sys_api`
VALUES (71, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 71, 'yesNo_yes', 'onOff_on',
        '编辑待办信息', 'todo', 'POST', '/todo/edit-todo');
INSERT INTO `sys_api`
VALUES (72, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 72, 'yesNo_yes', 'onOff_on', '新增待办',
        'todo', 'POST', '/todo/add-todo');
INSERT INTO `sys_api`
VALUES (73, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 73, 'yesNo_yes', 'onOff_on', '删除待办',
        'todo', 'POST', '/todo/delete-todo-by-id');
INSERT INTO `sys_api`
VALUES (74, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 74, 'yesNo_yes', 'onOff_on',
        '根据ID查找待办', 'todo', 'POST', '/todo/query-todo-by-id');
INSERT INTO `sys_api`
VALUES (75, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 75, 'yesNo_yes', 'onOff_on',
        '获取生成插件列表', 'genPlugin', 'POST', '/gen-plugin/get-gen-plugin-list');
INSERT INTO `sys_api`
VALUES (76, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 76, 'yesNo_yes', 'onOff_on', '生成插件',
        'genPlugin', 'POST', '/gen-plugin/gen-plugin');
INSERT INTO `sys_api`
VALUES (77, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 77, 'yesNo_yes', 'onOff_on',
        '删除生成插件', 'genPlugin', 'POST', '/gen-plugin/delete-gen-plugin-by-id');
INSERT INTO `sys_api`
VALUES (78, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 78, 'yesNo_yes', 'onOff_on',
        '下载生成插件', 'genPlugin', 'POST', '/gen-plugin/download-gen-plugin-by-id');
INSERT INTO `sys_api`
VALUES (79, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 79, 'yesNo_yes', 'onOff_on',
        '获取在线用户列表', 'user-online', 'POST', '/user-online/get-user-online-list');
INSERT INTO `sys_api`
VALUES (80, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 80, 'yesNo_yes', 'onOff_on',
        '踢出在线用户', 'user-online', 'POST', '/user-online/kick-online-user');
INSERT INTO `sys_api`
VALUES (81, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 81, 'yesNo_yes', 'onOff_on',
        '获取定时任务列表', 'cron', 'POST', '/cron/get-cron-list');
INSERT INTO `sys_api`
VALUES (82, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 82, 'yesNo_yes', 'onOff_on',
        '启动定时任务', 'cron', 'POST', '/cron/start-cron');
INSERT INTO `sys_api`
VALUES (83, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 83, 'yesNo_yes', 'onOff_on',
        '停止定时任务', 'cron', 'POST', '/cron/stop-cron');
INSERT INTO `sys_api`
VALUES (84, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1101, 'yes', 'onOff_on',
        '插件example：获取test-data-list', 'plugin-example', 'POST', '/plugin-example/get-test-data-list');
INSERT INTO `sys_api`
VALUES (85, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1102, 'yes', 'onOff_on',
        '插件example：编辑test-data信息', 'plugin-example', 'POST', '/plugin-example/edit-test-data');
INSERT INTO `sys_api`
VALUES (86, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1103, 'yes', 'onOff_on',
        '插件example：新增test-data', 'plugin-example', 'POST', '/plugin-example/add-test-data');
INSERT INTO `sys_api`
VALUES (87, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1104, 'yes', 'onOff_on',
        '插件example：删除test-data', 'plugin-example', 'POST', '/plugin-example/delete-test-data-by-id');
INSERT INTO `sys_api`
VALUES (88, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1105, 'yes', 'onOff_on',
        '插件example：根据ID查找test-data', 'plugin-example', 'POST', '/plugin-example/query-test-data-by-id');
INSERT INTO `sys_api`
VALUES (89, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1106, 'yes', 'onOff_on',
        '插件example：下载test-data模板', 'plugin-example', 'POST', '/plugin-example/download-template-test-data');
INSERT INTO `sys_api`
VALUES (90, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1107, 'yes', 'onOff_on',
        '插件example：导出test-data', 'plugin-example', 'POST', '/plugin-example/export-test-data');
INSERT INTO `sys_api`
VALUES (91, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1108, 'yes', 'onOff_on',
        '插件example：导入test-data', 'plugin-example', 'POST', '/plugin-example/import-test-data');
INSERT INTO `sys_api`
VALUES (92, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1201, 'yesNo_yes', 'onOff_on',
        '插件achievement：获取Category-list', 'plugin-achievement', 'POST', '/plugin-achievement/get-category-list');
INSERT INTO `sys_api`
VALUES (93, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1202, 'yesNo_yes', 'onOff_on',
        '插件Achievement：编辑Category信息', 'plugin-achievement', 'POST', '/plugin-achievement/edit-category');
INSERT INTO `sys_api`
VALUES (94, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1203, 'yesNo_yes', 'onOff_on',
        '插件Achievement：新增Category', 'plugin-achievement', 'POST', '/plugin-achievement/add-category');
INSERT INTO `sys_api`
VALUES (95, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1204, 'yesNo_yes', 'onOff_on',
        '插件Achievement：删除Category', 'plugin-achievement', 'POST', '/plugin-achievement/delete-category-by-id');
INSERT INTO `sys_api`
VALUES (96, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1205, 'yesNo_yes', 'onOff_on',
        '插件Achievement：根据ID查找Category', 'plugin-achievement', 'POST', '/plugin-achievement/query-category-by-id');
INSERT INTO `sys_api`
VALUES (97, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1206, 'yesNo_yes', 'onOff_on',
        '插件achievement：获取Obtain-list', 'plugin-achievement', 'POST', '/plugin-achievement/get-obtain-list');
INSERT INTO `sys_api`
VALUES (98, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1207, 'yesNo_yes', 'onOff_on',
        '插件Achievement：查找是否获取了成就', 'plugin-achievement', 'POST', '/plugin-achievement/obtain-find');

-- ----------------------------
-- Table structure for sys_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button`
(
    `menu_name`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单Name',
    `button_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '按钮名称',
    `button_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '按钮编码',
    PRIMARY KEY (`menu_name`, `button_code`) USING BTREE,
    UNIQUE INDEX `button_code` (`button_code` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_button
-- ----------------------------
INSERT INTO `sys_button`
VALUES ('api', '新增Api', 'api:add');
INSERT INTO `sys_button`
VALUES ('api', '删除', 'api:delete');
INSERT INTO `sys_button`
VALUES ('api', '编辑', 'api:edit');
INSERT INTO `sys_button`
VALUES ('config-backend', '新增配置', 'config-backend:add');
INSERT INTO `sys_button`
VALUES ('config-backend', '删除', 'config-backend:delete');
INSERT INTO `sys_button`
VALUES ('config-backend', '重置', 'config-backend:reset');
INSERT INTO `sys_button`
VALUES ('config-backend', '保存', 'config-backend:save');
INSERT INTO `sys_button`
VALUES ('config-frontend', '新增配置', 'config-frontend:add');
INSERT INTO `sys_button`
VALUES ('config-frontend', '删除', 'config-frontend:delete');
INSERT INTO `sys_button`
VALUES ('config-frontend', '重置', 'config-frontend:reset');
INSERT INTO `sys_button`
VALUES ('config-frontend', '保存', 'config-frontend:save');
INSERT INTO `sys_button`
VALUES ('dept', '新增子级部门', 'dept:addChildren');
INSERT INTO `sys_button`
VALUES ('dept', '新增父级部门', 'dept:addParent');
INSERT INTO `sys_button`
VALUES ('dept', '删除', 'dept:delete');
INSERT INTO `sys_button`
VALUES ('dept', '部门用户', 'dept:deptUser');
INSERT INTO `sys_button`
VALUES ('dept', '编辑', 'dept:edit');
INSERT INTO `sys_button`
VALUES ('dict', '新增子级字典', 'dict:addChildren');
INSERT INTO `sys_button`
VALUES ('dict', '新增父级字典', 'dict:addParent');
INSERT INTO `sys_button`
VALUES ('dict', '删除', 'dict:delete');
INSERT INTO `sys_button`
VALUES ('dict', '编辑', 'dict:edit');
INSERT INTO `sys_button`
VALUES ('genPlugin', '新增插件', 'genPlugin:add');
INSERT INTO `sys_button`
VALUES ('genPlugin', '下载插件', 'genPlugin:delete');
INSERT INTO `sys_button`
VALUES ('genPlugin', '下载插件', 'genPlugin:download');
INSERT INTO `sys_button`
VALUES ('genPlugin', '生成插件', 'genPlugin:gen');
INSERT INTO `sys_button`
VALUES ('log-login', '删除', 'log-login:delete');
INSERT INTO `sys_button`
VALUES ('log-login', '详情', 'log-login:detail');
INSERT INTO `sys_button`
VALUES ('log-operation', '删除', 'log-operation:delete');
INSERT INTO `sys_button`
VALUES ('log-operation', '详情', 'log-operation:detail');
INSERT INTO `sys_button`
VALUES ('menu', '新增子级菜单', 'menu:addChildren');
INSERT INTO `sys_button`
VALUES ('menu', '新增父级菜单', 'menu:addParent');
INSERT INTO `sys_button`
VALUES ('menu', '按钮注册', 'menu:buttonRegister');
INSERT INTO `sys_button`
VALUES ('menu', '删除', 'menu:delete');
INSERT INTO `sys_button`
VALUES ('menu', '编辑', 'menu:edit');
INSERT INTO `sys_button`
VALUES ('notice', '新增消息', 'notice:add');
INSERT INTO `sys_button`
VALUES ('notice', '删除', 'notice:delete');
INSERT INTO `sys_button`
VALUES ('notice', '编辑', 'notice:edit');
INSERT INTO `sys_button`
VALUES ('notice', '发送', 'notice:send');
INSERT INTO `sys_button`
VALUES ('role', '新增角色', 'role:add');
INSERT INTO `sys_button`
VALUES ('role', '删除', 'role:delete');
INSERT INTO `sys_button`
VALUES ('role', '编辑', 'role:edit');
INSERT INTO `sys_button`
VALUES ('role', '角色权限', 'role:permission');
INSERT INTO `sys_button`
VALUES ('role', '角色用户', 'role:user');
INSERT INTO `sys_button`
VALUES ('user', '新增用户', 'user:add');
INSERT INTO `sys_button`
VALUES ('user', '删除', 'user:delete');
INSERT INTO `sys_button`
VALUES ('user', '编辑', 'user:edit');
INSERT INTO `sys_button`
VALUES ('user', '重置密码', 'user:password');
INSERT INTO `sys_button`
VALUES ('user-online', '踢出', 'user-online:kick');

-- ----------------------------
-- Table structure for sys_config_backend
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_backend`;
CREATE TABLE `sys_config_backend`
(
    `id`           bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`   datetime                                                      NULL DEFAULT NULL,
    `created_by`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`   datetime                                                      NULL DEFAULT NULL,
    `updated_by`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`   datetime                                                      NULL DEFAULT NULL,
    `sort`         bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`         text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `config_item`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置项',
    `item_default` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '默认值',
    `item_custom`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义值',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_config_backend_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_config_backend_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_sys_config_backend_config_item` (`config_item` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config_backend
-- ----------------------------
INSERT INTO `sys_config_backend`
VALUES (1, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '用户初始密码',
        'defaultPassword', 'admin123', '');
INSERT INTO `sys_config_backend`
VALUES (2, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '验证码字符数',
        'captchaKeyLong', '4', '');
INSERT INTO `sys_config_backend`
VALUES (3, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 3, 'yesNo_yes', 'onOff_on',
        '验证码图片宽度', 'captchaWidth', '240', '');
INSERT INTO `sys_config_backend`
VALUES (4, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 4, 'yesNo_yes', 'onOff_on',
        '验证码图片高度', 'captchaHeight', '80', '');
INSERT INTO `sys_config_backend`
VALUES (5, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 5, 'yesNo_yes', 'onOff_on', 'Jwt签发者',
        'jwtIssuer', 'Cili-Quasar-Admin', '');
INSERT INTO `sys_config_backend`
VALUES (6, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 6, 'yesNo_yes', 'onOff_on', 'Jwt签发者密钥',
        'jwtKey', 'Cili-Quasar-Admin', '');
INSERT INTO `sys_config_backend`
VALUES (7, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 7, 'yesNo_yes', 'onOff_on',
        'Jwt过期时间(秒)', 'jwtExpiresAt', '14400', '');
INSERT INTO `sys_config_backend`
VALUES (8, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 8, 'yesNo_yes', 'onOff_on',
        'Jwt刷新时间(秒)', 'jwtRefreshAt', '86400', '');
INSERT INTO `sys_config_backend`
VALUES (9, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 9, 'yesNo_yes', 'onOff_on',
        '头像最大上传(M)', 'avatarMaxSize', '3', '');
INSERT INTO `sys_config_backend`
VALUES (10, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 10, 'yesNo_yes', 'onOff_on',
        '头像允许后缀', 'avatarExt', '.png,.jpg', '');
INSERT INTO `sys_config_backend`
VALUES (11, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 11, 'yesNo_yes', 'onOff_on',
        '文件最大上传(M)', 'fileMaxSize', '10', '');
INSERT INTO `sys_config_backend`
VALUES (12, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 12, 'yesNo_yes', 'onOff_on',
        '文件允许后缀', 'fileExt', '.png,.jpg,.docx,.xlsx,.txt,.doc,.xls,.zip', '');
INSERT INTO `sys_config_backend`
VALUES (13, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 13, 'yesNo_yes', 'onOff_on',
        '网站Logo最大上传(M)', 'logoMaxSize', '2', '');
INSERT INTO `sys_config_backend`
VALUES (14, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 14, 'yesNo_yes', 'onOff_on',
        '网站Logo允许后缀', 'logoExt', '.png,.jpg', '');
INSERT INTO `sys_config_backend`
VALUES (15, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 15, 'yesNo_yes', 'onOff_on',
        '网站Favicon最大上传(M)', 'faviconMaxSize', '1', '');
INSERT INTO `sys_config_backend`
VALUES (16, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 16, 'yesNo_yes', 'onOff_on',
        '网站Favicon允许后缀', 'faviconExt', '.ico', '');
INSERT INTO `sys_config_backend`
VALUES (17, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 17, 'yesNo_yes', 'onOff_on',
        '网站首页大图最大上传(M)', 'bannerImageMaxSize', '4', '');
INSERT INTO `sys_config_backend`
VALUES (18, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 18, 'yesNo_yes', 'onOff_on',
        '网站首页大图允许后缀', 'bannerImageExt', '.png,.jpg', '');
INSERT INTO `sys_config_backend`
VALUES (19, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 19, 'yesNo_yes', 'onOff_on',
        '上传头像保存位置', 'uploadAvatarSavePath', 'avatar', '');
INSERT INTO `sys_config_backend`
VALUES (20, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 20, 'yesNo_yes', 'onOff_on',
        '上传文件保存位置', 'uploadFileSavePath', 'file', '');
INSERT INTO `sys_config_backend`
VALUES (21, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 21, 'yesNo_yes', 'onOff_on',
        '上传logo保存位置', 'uploadLogoSavePath', 'logo', '');
INSERT INTO `sys_config_backend`
VALUES (22, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 22, 'yesNo_yes', 'onOff_on',
        '上传首页大图保存位置', 'uploadBannerImageSavePath', 'banner', '');

-- ----------------------------
-- Table structure for sys_config_frontend
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_frontend`;
CREATE TABLE `sys_config_frontend`
(
    `id`           bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`   datetime                                                      NULL DEFAULT NULL,
    `created_by`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`   datetime                                                      NULL DEFAULT NULL,
    `updated_by`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`   datetime                                                      NULL DEFAULT NULL,
    `sort`         bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`         text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `config_item`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置项',
    `item_default` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '默认值',
    `item_custom`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义值',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_config_frontend_config_item` (`config_item` ASC) USING BTREE,
    INDEX `idx_sys_config_frontend_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_config_frontend_deleted_at` (`deleted_at` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config_frontend
-- ----------------------------
INSERT INTO `sys_config_frontend`
VALUES (1, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '首页大图',
        'bannerImage', '', '');
INSERT INTO `sys_config_frontend`
VALUES (2, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '网站主标题',
        'mainTitle', 'Cilicili-Admin', ' CiliCili(゜-゜)つロ 干杯~');
INSERT INTO `sys_config_frontend`
VALUES (3, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 3, 'yesNo_yes', 'onOff_on', '网站副标题',
        'subTitle', 'Cilicili后台管理系统', '');
INSERT INTO `sys_config_frontend`
VALUES (4, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 4, 'yesNo_yes', 'onOff_on', '网站描述',
        'webDescribe', 'Lorem ipsum dolor sit amet consectetur adipisicing elit', '');
INSERT INTO `sys_config_frontend`
VALUES (5, '2023-12-04 13:02:17', 'admin', '2024-01-24 20:28:54', 'admin', NULL, 5, 'yesNo_yes', 'onOff_on',
        '登录页风格', 'loginLayoutStyle', 'loginLayoutStyle_login', '');
INSERT INTO `sys_config_frontend`
VALUES (6, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 6, 'yesNo_yes', 'onOff_on', '登录页插件',
        'pluginLoginLayout', '', '');
INSERT INTO `sys_config_frontend`
VALUES (7, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 7, 'yesNo_yes', 'onOff_on', '网站Logo',
        'logo', '', '');
INSERT INTO `sys_config_frontend`
VALUES (8, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 8, 'yesNo_yes', 'onOff_on', '网站favicon',
        'favicon', '', '');
INSERT INTO `sys_config_frontend`
VALUES (9, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 9, 'yesNo_yes', 'onOff_on', '显示仓库入口',
        'showGit', 'yesNo_yes', '');
INSERT INTO `sys_config_frontend`
VALUES (10, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 10, 'yesNo_yes', 'onOff_on', '星星颜色',
        'starColor', '#00b4ff', '');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`          bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`  datetime                                                      NULL DEFAULT NULL,
    `created_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`  datetime                                                      NULL DEFAULT NULL,
    `updated_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`  datetime                                                      NULL DEFAULT NULL,
    `sort`        bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `parent_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父部门DeptCode',
    `dept_code`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编码',
    `dept_name`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
    `leader`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门负责人username',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_sys_dept_dept_code` (`dept_code` ASC) USING BTREE,
    INDEX `idx_sys_dept_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_dept_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_sys_dept_parent_code` (`parent_code` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept`
VALUES (1, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_no', 'onOff_on',
        '这是Cili-Quasar-Admin部门', '', 'cili-quasar-admin', 'Cili-Quasar-Admin', 'admin');
INSERT INTO `sys_dept`
VALUES (2, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_no', 'onOff_on', '这是Cili部门',
        'cili-quasar-admin', 'cil', 'Cil', 'admin');
INSERT INTO `sys_dept`
VALUES (3, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_no', 'onOff_on', '这是Quasar部门',
        'cili-quasar-admin', 'quasar', 'Quasar', 'admin');

-- ----------------------------
-- Table structure for sys_dept_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_user`;
CREATE TABLE `sys_dept_user`
(
    `sys_dept_dept_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '\'部门编码\'',
    `sys_user_username`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '\'用户名\'',
    PRIMARY KEY (`sys_dept_dept_code`, `sys_user_username`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept_user
-- ----------------------------
INSERT INTO `sys_dept_user`
VALUES ('cili', 'test001');
INSERT INTO `sys_dept_user`
VALUES ('cili-quasar-admin', 'admin');
INSERT INTO `sys_dept_user`
VALUES ('cili-quasar-admin', 'test001');
INSERT INTO `sys_dept_user`
VALUES ('quasar', 'test001');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`          bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`  datetime                                                      NULL DEFAULT NULL,
    `created_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`  datetime                                                      NULL DEFAULT NULL,
    `updated_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`  datetime                                                      NULL DEFAULT NULL,
    `sort`        bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `parent_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父字典编码',
    `dict_code`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
    `dict_label`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
    `dict_ext1`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典扩展项1',
    `dict_ext2`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典扩展项2',
    `dict_ext3`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典扩展项3',
    `dict_ext4`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典扩展项4',
    `dict_ext5`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典扩展项5',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_sys_dict_dict_code` (`dict_code` ASC) USING BTREE,
    INDEX `idx_sys_dict_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_dict_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_sys_dict_parent_code` (`parent_code` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict`
VALUES (1, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '性别字典', '',
        'gender', '性别', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (2, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '男', 'gender',
        'gender_male', 'Male', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (3, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '女', 'gender',
        'gender_female', 'Female', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (4, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 3, 'yesNo_yes', 'onOff_on', '保密',
        'gender', 'gender_unknown', 'Unknown', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (5, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '启用状态', '',
        'onOff', '启用状态', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (6, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '启用', 'onOff',
        'onOff_on', 'On', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (7, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '禁用', 'onOff',
        'onOff_off', 'Off', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (8, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 3, 'yesNo_yes', 'onOff_on', '是否状态', '',
        'yesNo', '是否状态', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (9, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '是', 'yesNo',
        'yesNo_yes', 'Yes', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (10, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '否', 'yesNo',
        'yesNo_no', 'No', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (11, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 4, 'yesNo_yes', 'onOff_on', '部门数据权限',
        '', 'deptDataPermissionType', '部门数据权限', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (12, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on',
        '全部部门数据权限', 'deptDataPermissionType', 'deptDataPermissionType_all', '全部部门数据权限', '', '', '', '',
        '');
INSERT INTO `sys_dict`
VALUES (13, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on',
        '用户本人数据权限', 'deptDataPermissionType', 'deptDataPermissionType_user', '用户本人数据权限', '', '', '', '',
        '');
INSERT INTO `sys_dict`
VALUES (14, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 3, 'yesNo_yes', 'onOff_on',
        '用户所在部门数据权限', 'deptDataPermissionType', 'deptDataPermissionType_dept', '用户所在部门数据权限', '', '',
        '', '', '');
INSERT INTO `sys_dict`
VALUES (15, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 4, 'yesNo_yes', 'onOff_on',
        '用户所在部门及子部门数据权限', 'deptDataPermissionType', 'deptDataPermissionType_deptAndChildren',
        '用户所在部门及子部门数据权限', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (16, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 5, 'yesNo_yes', 'onOff_on',
        '自定义部门数据权限', 'deptDataPermissionType', 'deptDataPermissionType_custom', '自定义部门数据权限', '', '',
        '', '', '');
INSERT INTO `sys_dict`
VALUES (17, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 5, 'yesNo_yes', 'onOff_on', '消息类型', '',
        'noticeType', '消息类型', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (18, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '系统消息',
        'noticeType', 'noticeType_system', '系统消息', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (19, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '消息提示',
        'noticeType', 'noticeType_message', '消息提示', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (20, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 6, 'yesNo_yes', 'onOff_on', '登录页风格',
        '', 'loginLayoutStyle', '登录页风格', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (21, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '登录页',
        'loginLayoutStyle', 'loginLayoutStyle_login', '登录页', '', '', '', '', '');
INSERT INTO `sys_dict`
VALUES (22, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '门户页',
        'loginLayoutStyle', 'loginLayoutStyle_portal', '门户页', '', '', '', '', '');

-- ----------------------------
-- Table structure for sys_gen_plugin_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_gen_plugin_list`;
CREATE TABLE `sys_gen_plugin_list`
(
    `id`          bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`  datetime                                                      NULL DEFAULT NULL,
    `created_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`  datetime                                                      NULL DEFAULT NULL,
    `updated_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`  datetime                                                      NULL DEFAULT NULL,
    `sort`        bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `plugin_sort` bigint UNSIGNED                                               NULL DEFAULT NULL COMMENT '插件排序',
    `plugin_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '插件编码',
    `plugin_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '插件名称',
    `plugin_file` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '插件位置',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_gen_plugin_list_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_gen_plugin_list_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_sys_gen_plugin_list_plugin_code` (`plugin_code` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_gen_plugin_list
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login`
(
    `id`             bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`     datetime                                                      NULL DEFAULT NULL,
    `created_by`     varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`     datetime                                                      NULL DEFAULT NULL,
    `updated_by`     varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`     datetime                                                      NULL DEFAULT NULL,
    `sort`           bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`           text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `login_username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录用户名',
    `login_ip`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录IP',
    `login_browser`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录浏览器',
    `login_os`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录操作系统',
    `login_platform` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录平台',
    `login_success`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_log_login_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_log_login_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_sys_log_login_login_username` (`login_username` ASC) USING BTREE,
    INDEX `idx_sys_log_login_login_ip` (`login_ip` ASC) USING BTREE,
    INDEX `idx_sys_log_login_login_success` (`login_success` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
INSERT INTO `sys_log_login`
VALUES (13, '2024-01-24 19:50:13', 'admin', '2024-01-24 19:50:23', NULL, NULL, 1, 'yesNo_no', 'onOff_on',
        '登录成功. Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36',
        'SuperAdmin', '127.0.0.1', 'Chrome 121.0.0.0', 'Windows 10 or Windows Server 2016', 'Windows', 'yesNo_yes');
INSERT INTO `sys_log_login`
VALUES (14, '2024-01-24 19:50:33', 'admin', '2024-01-24 19:50:54', NULL, NULL, 1, 'yesNo_no', 'onOff_on',
        '登录成功. Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36',
        'SuperAdmin', '127.0.0.1', 'Chrome 121.0.0.0', 'Windows 10 or Windows Server 2016', 'Windows', 'yesNo_yes');
INSERT INTO `sys_log_login`
VALUES (15, '2024-01-24 19:51:33', 'admin', '2024-01-24 19:51:53', NULL, NULL, 1, 'yesNo_no', 'onOff_on',
        '登录成功. Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36',
        'SuperAdmin', '127.0.0.1', 'Chrome 121.0.0.0', 'Windows 10 or Windows Server 2016', 'Windows', 'yesNo_yes');
INSERT INTO `sys_log_login`
VALUES (16, '2024-01-24 20:20:20', 'admin', '2024-01-24 20:28:36', NULL, NULL, 1, 'yesNo_no', 'onOff_on',
        '登录成功. Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36',
        'SuperAdmin', '127.0.0.1', 'Chrome 121.0.0.0', 'Windows 10 or Windows Server 2016', 'Windows', 'yesNo_yes');
INSERT INTO `sys_log_login`
VALUES (17, '2024-01-24 20:28:47', 'admin', '2024-01-24 20:29:01', NULL, NULL, 1, 'yesNo_no', 'onOff_on',
        '登录成功. Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36',
        'SuperAdmin', '127.0.0.1', 'Chrome 121.0.0.0', 'Windows 10 or Windows Server 2016', 'Windows', 'yesNo_yes');
INSERT INTO `sys_log_login`
VALUES (18, '2024-01-24 21:03:08', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', 'onOff_on',
        '该账号已在别处登录！ Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36',
        'admin', '127.0.0.1', 'Chrome 121.0.0.0', 'Windows 10 or Windows Server 2016', 'Windows', 'yesNo_no');

-- ----------------------------
-- Table structure for sys_log_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operation`;
CREATE TABLE `sys_log_operation`
(
    `id`                 bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`         datetime                                                      NULL DEFAULT NULL,
    `created_by`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`         datetime                                                      NULL DEFAULT NULL,
    `updated_by`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`         datetime                                                      NULL DEFAULT NULL,
    `sort`               bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`             varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`             varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`               text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `operation_username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求用户名',
    `operation_ip`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求IP',
    `operation_method`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
    `operation_api`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求Api',
    `operation_status`   bigint                                                        NULL DEFAULT NULL COMMENT '请求状态',
    `operation_body`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT 'body',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_log_operation_operation_username` (`operation_username` ASC) USING BTREE,
    INDEX `idx_sys_log_operation_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_log_operation_deleted_at` (`deleted_at` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 141
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log_operation
-- ----------------------------
INSERT INTO `sys_log_operation`
VALUES (1, '2024-01-24 19:50:00', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/init-db', 200, '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (2, '2024-01-24 19:50:00', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (3, '2024-01-24 19:50:00', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (4, '2024-01-24 19:50:00', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (5, '2024-01-24 19:50:00', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (6, '2024-01-24 19:50:01', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (7, '2024-01-24 19:50:01', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (8, '2024-01-24 19:50:02', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (9, '2024-01-24 19:50:04', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (10, '2024-01-24 19:50:04', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (11, '2024-01-24 19:50:06', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (12, '2024-01-24 19:50:06', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (13, '2024-01-24 19:50:08', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-captcha', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (14, '2024-01-24 19:50:14', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '登录成功.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/login', 200, '{\"code\":1,\"message\":\"登录成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (15, '2024-01-24 19:50:15', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (16, '2024-01-24 19:50:16', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin', '127.0.0.1',
        'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (17, '2024-01-24 19:50:23', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'admin 下线成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/public/logout', 200, '{\"code\":1,\"message\":\"admin 下线成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (18, '2024-01-24 19:50:23', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (19, '2024-01-24 19:50:23', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (20, '2024-01-24 19:50:24', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (21, '2024-01-24 19:50:25', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (22, '2024-01-24 19:50:27', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-captcha', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (23, '2024-01-24 19:50:33', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '登录成功.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/login', 200, '{\"code\":1,\"message\":\"登录成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (24, '2024-01-24 19:50:35', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (25, '2024-01-24 19:50:35', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin', '127.0.0.1',
        'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (26, '2024-01-24 19:50:37', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/config-frontend/get-config-frontend-list', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (27, '2024-01-24 19:50:48', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'admin修改成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/config-frontend/edit-config-frontend', 200,
        '{\"code\":1,\"message\":\"admin修改成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (28, '2024-01-24 19:50:50', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (29, '2024-01-24 19:50:53', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'admin 下线成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/public/logout', 200, '{\"code\":1,\"message\":\"admin 下线成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (30, '2024-01-24 19:50:54', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (31, '2024-01-24 19:50:54', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (32, '2024-01-24 19:50:55', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (33, '2024-01-24 19:50:56', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (34, '2024-01-24 19:51:24', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (35, '2024-01-24 19:51:24', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (36, '2024-01-24 19:51:25', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (37, '2024-01-24 19:51:26', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (38, '2024-01-24 19:51:33', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '登录成功.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/login', 200, '{\"code\":1,\"message\":\"登录成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (39, '2024-01-24 19:51:34', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (40, '2024-01-24 19:51:35', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin', '127.0.0.1',
        'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (41, '2024-01-24 19:51:42', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/config-frontend/get-config-frontend-list', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (42, '2024-01-24 19:51:46', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'admin修改成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/config-frontend/edit-config-frontend', 200,
        '{\"code\":1,\"message\":\"admin修改成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (43, '2024-01-24 19:51:48', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (44, '2024-01-24 19:51:53', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'admin 下线成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/public/logout', 200, '{\"code\":1,\"message\":\"admin 下线成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (45, '2024-01-24 19:51:53', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (46, '2024-01-24 19:51:53', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (47, '2024-01-24 19:51:54', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (48, '2024-01-24 19:51:55', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (49, '2024-01-24 19:52:04', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (50, '2024-01-24 19:52:04', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (51, '2024-01-24 19:52:05', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (52, '2024-01-24 19:52:08', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (53, '2024-01-24 19:54:01', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (54, '2024-01-24 19:54:01', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (55, '2024-01-24 19:54:02', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (56, '2024-01-24 19:54:03', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (57, '2024-01-24 19:54:34', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (58, '2024-01-24 19:54:34', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (59, '2024-01-24 19:54:35', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (60, '2024-01-24 19:54:36', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (61, '2024-01-24 20:18:31', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (62, '2024-01-24 20:18:31', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (63, '2024-01-24 20:18:33', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (64, '2024-01-24 20:18:35', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (65, '2024-01-24 20:18:53', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (66, '2024-01-24 20:18:55', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (67, '2024-01-24 20:18:57', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (68, '2024-01-24 20:18:58', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (69, '2024-01-24 20:20:14', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-captcha', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (70, '2024-01-24 20:20:20', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '登录成功.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/login', 200, '{\"code\":1,\"message\":\"登录成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (71, '2024-01-24 20:20:26', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (72, '2024-01-24 20:20:50', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin', '127.0.0.1',
        'POST', '/public/get-captcha', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (73, '2024-01-24 20:21:07', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (74, '2024-01-24 20:21:53', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (75, '2024-01-24 20:22:46', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (76, '2024-01-24 20:23:57', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (77, '2024-01-24 20:24:34', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (78, '2024-01-24 20:25:31', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (79, '2024-01-24 20:26:52', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (80, '2024-01-24 20:26:54', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (81, '2024-01-24 20:28:06', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (82, '2024-01-24 20:28:09', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin', '127.0.0.1',
        'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (83, '2024-01-24 20:28:36', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'admin 下线成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/public/logout', 200, '{\"code\":1,\"message\":\"admin 下线成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (84, '2024-01-24 20:28:38', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (85, '2024-01-24 20:28:38', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (86, '2024-01-24 20:28:39', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (87, '2024-01-24 20:28:39', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (88, '2024-01-24 20:28:41', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-captcha', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (89, '2024-01-24 20:28:47', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '登录成功.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/login', 200, '{\"code\":1,\"message\":\"登录成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (90, '2024-01-24 20:28:48', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (91, '2024-01-24 20:28:48', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin', '127.0.0.1',
        'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (92, '2024-01-24 20:28:50', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/config-frontend/get-config-frontend-list', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (93, '2024-01-24 20:28:54', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'admin修改成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/config-frontend/edit-config-frontend', 200,
        '{\"code\":1,\"message\":\"admin修改成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (94, '2024-01-24 20:28:56', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (95, '2024-01-24 20:29:01', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'admin 下线成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/public/logout', 200, '{\"code\":1,\"message\":\"admin 下线成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (96, '2024-01-24 20:29:01', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (97, '2024-01-24 20:29:01', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (98, '2024-01-24 20:29:02', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (99, '2024-01-24 20:29:03', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (100, '2024-01-24 20:29:05', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (101, '2024-01-24 20:29:05', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (102, '2024-01-24 20:29:08', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (103, '2024-01-24 20:29:08', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '登录成功.',
        'anonymousUser', '127.0.0.1', 'POST', '/public/login', 200, '{\"code\":1,\"message\":\"登录成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (104, '2024-01-24 20:29:08', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (105, '2024-01-24 20:29:09', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (106, '2024-01-24 20:29:09', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (107, '2024-01-24 20:29:20', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/dept/get-dept-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (108, '2024-01-24 20:29:28', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '查找成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/dept/query-dept-by-id', 200, '{\"code\":1,\"message\":\"查找成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (109, '2024-01-24 20:29:52', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/api/get-api-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (110, '2024-01-24 20:30:14', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/role/get-role-list', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (111, '2024-01-24 20:30:16', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/dept/get-dept-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (112, '2024-01-24 20:30:21', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/menu/get-menu-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (113, '2024-01-24 20:30:26', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/dict/get-dict-list', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (114, '2024-01-24 20:30:29', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/api/get-api-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (115, '2024-01-24 20:30:33', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/role/get-role-list', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (116, '2024-01-24 20:30:35', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/config-frontend/get-config-frontend-list', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (117, '2024-01-24 20:30:37', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (118, '2024-01-24 20:30:37', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/dept/get-dept-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (119, '2024-01-24 20:30:39', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/config-frontend/get-config-frontend-list', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (120, '2024-01-24 20:30:40', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/config-backend/get-config-backend-list', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (121, '2024-01-24 20:30:45', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/log/get-log-login-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (122, '2024-01-24 20:30:48', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/log/get-log-operation-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (123, '2024-01-24 20:30:54', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (124, '2024-01-24 20:32:06', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/menu/get-menu-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (125, '2024-01-24 20:32:09', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '查找成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/menu/query-menu-by-id', 200, '{\"code\":1,\"message\":\"查找成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (126, '2024-01-24 20:45:18', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (127, '2024-01-24 20:45:19', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (128, '2024-01-24 20:45:22', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (129, '2024-01-24 20:45:22', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (130, '2024-01-24 20:45:23', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/menu/get-menu-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (131, '2024-01-24 20:48:02', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/dept/get-dept-list', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (132, '2024-01-24 20:56:55', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/init-db', 200, '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (133, '2024-01-24 20:56:55', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (134, '2024-01-24 20:56:55', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (135, '2024-01-24 20:56:55', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (136, '2024-01-24 20:56:55', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (137, '2024-01-24 20:56:55', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (138, '2024-01-24 20:56:56', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (139, '2024-01-24 20:56:56', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (140, '2024-01-24 20:56:57', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL,
        '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown database \'quasar-admin\'\r\n### The error may exist in file [D:\\Documents\\JavaCode\\Code\\cilicili(嗤哩嗤哩)\\build\\resources\\main\\mapper\\sysUserOnline\\SysUserOnlineMapping.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select username,         token         from sys_user_online          WHERE username = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown database \'quasar-admin\'\n; bad SQL grammar []',
        'anonymousUser', '127.0.0.1', 'POST', '/public/login', 422,
        '{\"headers\":{},\"body\":{\"body\":[\"\\r\\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown database \'quasar-admin\'\\r\\n### The error may exist in file [D:\\\\Documents\\\\JavaCode\\\\Code\\\\cilicili(嗤哩嗤哩)\\\\build\\\\resources\\\\main\\\\mapper\\\\sysUserOnline\\\\SysUserOnlineMapping.xml]\\r\\n### The error may involve defaultParameterMap\\r\\n### The error occurred while setting parameters\\r\\n### SQL: select username,         token         from sys_user_online          WHERE username = ?\\r\\n### Cause: java.sql.SQLSyntaxErrorException: Unknown database \'quasar-admin\'\\n; bad SQL grammar []\"]},\"status_code\":\"UNPROCESSABLE_ENTITY\",\"status_code_value\":422}');
INSERT INTO `sys_log_operation`
VALUES (141, '2024-01-24 21:03:06', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '数据库初始化成功！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/check-db', 200,
        '{\"code\":1,\"message\":\"数据库初始化成功！\"}');
INSERT INTO `sys_log_operation`
VALUES (142, '2024-01-24 21:03:07', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-dict-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (143, '2024-01-24 21:03:08', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '该账号已在别处登录！',
        'anonymousUser', '127.0.0.1', 'POST', '/public/login', 200, '{\"code\":0,\"message\":\"该账号已在别处登录！\"}');
INSERT INTO `sys_log_operation`
VALUES (144, '2024-01-24 21:03:08', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-frontend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (145, '2024-01-24 21:03:08', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-captcha', 200, '{\"code\":1,\"message\":\"Success\"}');
INSERT INTO `sys_log_operation`
VALUES (146, '2024-01-24 21:03:09', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success.', 'anonymousUser',
        '127.0.0.1', 'POST', '/public/get-config-backend-all', 200, '{\"code\":1,\"message\":\"Success.\"}');
INSERT INTO `sys_log_operation`
VALUES (147, '2024-01-24 21:03:23', 'anonymousUser', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '登录成功.',
        'anonymousUser', '127.0.0.1', 'POST', '/public/login', 200, '{\"code\":1,\"message\":\"登录成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (148, '2024-01-24 21:03:24', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, '获取用户菜单成功.', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user/get-user-menu', 200, '{\"code\":1,\"message\":\"获取用户菜单成功.\"}');
INSERT INTO `sys_log_operation`
VALUES (149, '2024-01-24 21:03:28', 'admin', NULL, NULL, NULL, 1, 'yesNo_no', NULL, 'Success', 'SuperAdmin',
        '127.0.0.1', 'POST', '/user-online/get-user-online-list', 200, '{\"code\":1,\"message\":\"Success\"}');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`  datetime                                                      NULL DEFAULT NULL,
    `created_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`  datetime                                                      NULL DEFAULT NULL,
    `updated_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`  datetime                                                      NULL DEFAULT NULL,
    `sort`        bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `parent_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父菜单Name',
    `name`        varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单Name',
    `path`        varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
    `component`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端组件',
    `redirect`    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '重定向地址',
    `title`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
    `icon`        varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
    `hidden`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'yesNo_no' COMMENT '是否在列表隐藏',
    `keep_alive`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'yesNo_no' COMMENT '是否缓存',
    `is_link`     varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'yesNo_no' COMMENT '是否外链',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name` (`name` ASC) USING BTREE,
    INDEX `idx_sys_menu_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_menu_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_sys_menu_parent_code` (`parent_code` ASC) USING BTREE,
    INDEX `idx_sys_menu_name` (`name` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 31
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '系统管理', '',
        'system', '', '', '/dashboard', 'SystemManage', 'eva-settings-2-outline', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (2, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '仪表盘',
        'system', 'dashboard', '/dashboard', 'pages/Dashboard/index', '', 'Dashboard', 'eva-home-outline', 'yesNo_no',
        'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (3, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '部门管理',
        'system', 'dept', '/dept', 'pages/System/Dept/index', '', 'DeptManage', 'eva-grid-outline', 'yesNo_no',
        'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (4, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 3, 'yesNo_yes', 'onOff_on', '菜单管理',
        'system', 'menu', '/menu', 'pages/System/Menu/index', '', 'MenuManage', 'eva-menu', 'yesNo_no', 'yesNo_no',
        'yesNo_no');
INSERT INTO `sys_menu`
VALUES (5, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 4, 'yesNo_yes', 'onOff_on', '字典管理',
        'system', 'dict', '/dict', 'pages/System/Dict/index', '', 'DictManage', 'eva-book-open-outline', 'yesNo_no',
        'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (6, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 5, 'yesNo_yes', 'onOff_on', 'Api管理',
        'system', 'api', '/api', 'pages/System/Api/index', '', 'ApiManage', 'eva-link', 'yesNo_no', 'yesNo_no',
        'yesNo_no');
INSERT INTO `sys_menu`
VALUES (7, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 6, 'yesNo_yes', 'onOff_on', '角色管理',
        'system', 'role', '/role', 'pages/System/Role/index', '', 'RoleManage', 'eva-people-outline', 'yesNo_no',
        'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (8, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 7, 'yesNo_yes', 'onOff_on', '用户管理',
        'system', 'user', '/user', 'pages/System/User/index', '', 'UserManage', 'eva-person-outline', 'yesNo_no',
        'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (9, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 8, 'yesNo_yes', 'onOff_on', '前台配置管理',
        'system', 'config-frontend', '/config-frontend', 'pages/System/ConfigFrontend/index', '', 'FrontendManage',
        'eva-settings-outline', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (10, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 9, 'yesNo_yes', 'onOff_on', '后台配置管理',
        'system', 'config-backend', '/config-backend', 'pages/System/ConfigBackend/index', '', 'BackendManage',
        'eva-settings-outline', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (11, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 10, 'yesNo_yes', 'onOff_on', '系统日志',
        'system', 'log', '/log', '', '/log/log-login', 'LogManage', 'eva-printer-outline', 'yesNo_no', 'yesNo_no',
        'yesNo_no');
INSERT INTO `sys_menu`
VALUES (12, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '登录日志',
        'log', 'log-login', '/log/log-login', 'pages/System/Log/Login/index', '', 'LogLogin', 'eva-printer-outline',
        'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (13, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '操作日志',
        'log', 'log-operation', '/log/log-operation', 'pages/System/Log/Operation/index', '', 'LogOperation',
        'eva-printer-outline', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (14, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 11, 'yesNo_yes', 'onOff_on', '消息管理',
        'system', 'notice', '/notice', 'pages/System/Notice/index', '', 'NoticeManage', 'eva-message-circle-outline',
        'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (15, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 12, 'yesNo_yes', 'onOff_on', '插件生成',
        'system', 'genPlugin', '/gen-plugin', 'pages/System/GenPlugin/index', '', 'GenPlugin', 'eva-code-download',
        'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (16, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 13, 'yesNo_yes', 'onOff_on', '在线用户',
        'system', 'user-online', '/user-online', 'pages/System/UserOnline/index', '', 'UserOnline',
        'eva-monitor-outline', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (17, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 14, 'yesNo_yes', 'onOff_on', '定时任务',
        'system', 'cron', '/cron', 'pages/System/Cron/index', '', 'Cron', 'eva-clock-outline', 'yesNo_no', 'yesNo_no',
        'yesNo_no');
INSERT INTO `sys_menu`
VALUES (18, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 100, 'yesNo_yes', 'onOff_on', 'Gitee',
        'system', 'gitee', 'https://gitee.com/cby-cby', '', '', 'Gitee', 'fab fa-git', 'yesNo_no',
        'yesNo_no', 'yesNo_yes');
INSERT INTO `sys_menu`
VALUES (19, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1101, 'yesNo_yes', 'onOff_on',
        '这是示例插件', '', 'GqaPluginExample', '', '', '/plugin-example/example/icons', '示例插件', 'install_desktop',
        'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (20, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '这是图标合集',
        'GqaPluginExample', 'plugin-example-icons', '/plugin-example/example/icons', 'plugins/Example/Icons/index', '',
        '图标合集', 'insert_emoticon', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (21, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '这是编辑器',
        'GqaPluginExample', 'plugin-example-editor', '/plugin-example/example/editor', 'plugins/Example/Editor/index',
        '', '编辑器', 'edit', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (22, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 3, 'yesNo_yes', 'onOff_on', '这是树形表格',
        'GqaPluginExample', 'plugin-example-tree-table', '/plugin-example/example/tree-table',
        'plugins/Example/TreeTable/index', '', '树形表格', 'list_alt', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (23, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 4, 'yesNo_yes', 'onOff_on', '这是统计数据',
        'GqaPluginExample', 'plugin-example-statistic', '/plugin-example/example/statistic',
        'plugins/Example/Statistic/index', '', '统计数据', 'filter_9_plus', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (24, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 5, 'yesNo_yes', 'onOff_on',
        '这是级联选择器', 'GqaPluginExample', 'plugin-example-cascader', '/plugin-example/example/cascader',
        'plugins/Example/Cascader/index', '', '级联选择器', 'call_split', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (25, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 6, 'yesNo_yes', 'onOff_on',
        '这是数据导入导出', 'GqaPluginExample', 'plugin-example-import-export', '/plugin-example/example/import-export',
        'plugins/Example/ExportData/index', '', '导入导出', 'import_export', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (26, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 7, 'yesNo_yes', 'onOff_on', '这是上传组件',
        'GqaPluginExample', 'plugin-example-uploader', '/plugin-example/example/uploader',
        'plugins/Example/Uploader/index', '', '上传器', 'eva-cloud-upload-outline', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (27, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1201, 'yesNo_yes', 'onOff_on',
        '这是成就系统插件', '', 'GqaPluginAchievement', '', '', '/plugin-achievement/achievement/category', '成就插件',
        'fas fa-award', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (28, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 1, 'yesNo_yes', 'onOff_on', '这是Category',
        'GqaPluginAchievement', 'plugin-achievement-category', '/plugin-achievement/achievement/category',
        'plugins/Achievement/Category/index', '', '成就分类', 'fas fa-award', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (29, '2023-12-04 13:02:17', 'admin', '2023-12-04 13:02:58', '', NULL, 2, 'yesNo_yes', 'onOff_on', '这是Obtain',
        'GqaPluginAchievement', 'plugin-achievement-obtain', '/plugin-achievement/achievement/obtain',
        'plugins/Achievement/Obtain/index', '', '已获得成就', 'fas fa-award', 'yesNo_no', 'yesNo_no', 'yesNo_no');
INSERT INTO `sys_menu`
VALUES (30, '2023-12-04 18:02:31', 'admin', '2023-12-04 18:02:31', '', NULL, 1, 'yesNo_no', 'onOff_on', 'ss', '', 'ss',
        'ss', 'ss', '', 'ss', '/usr', 'yesNo_yes', 'yesNo_yes', 'yesNo_yes');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `id`                  bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`          datetime                                                      NULL DEFAULT NULL,
    `created_by`          varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`          datetime                                                      NULL DEFAULT NULL,
    `updated_by`          varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`          datetime                                                      NULL DEFAULT NULL,
    `sort`                bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`              varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`              varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`                text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `notice_id`           varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息ID',
    `notice_title`        varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息题目',
    `notice_content`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '消息内容',
    `notice_type`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息类型',
    `notice_sent`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'yesNo_no' COMMENT '已经发送',
    `notice_to_user_type` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收用户范围',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_notice_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_notice_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_sys_notice_notice_id` (`notice_id` ASC) USING BTREE,
    INDEX `idx_sys_notice_notice_title` (`notice_title` ASC) USING BTREE,
    INDEX `idx_sys_notice_notice_type` (`notice_type` ASC) USING BTREE,
    INDEX `idx_sys_notice_notice_sent` (`notice_sent` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_notice_to_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_to_user`;
CREATE TABLE `sys_notice_to_user`
(
    `notice_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息ID',
    `to_user`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收用户',
    `user_read` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'yesNo_no' COMMENT '是否阅读',
    INDEX `idx_sys_notice_to_user_user_read` (`user_read` ASC) USING BTREE,
    INDEX `idx_sys_notice_to_user_notice_id` (`notice_id` ASC) USING BTREE,
    INDEX `idx_sys_notice_to_user_to_user` (`to_user` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice_to_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`                          bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`                  datetime                                                      NULL     DEFAULT NULL,
    `created_by`                  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `updated_at`                  datetime                                                      NULL     DEFAULT NULL,
    `updated_by`                  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `deleted_at`                  datetime                                                      NULL     DEFAULT NULL,
    `sort`                        bigint UNSIGNED                                               NULL     DEFAULT 1 COMMENT '排序',
    `stable`                      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`                      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT 'onOff_on' COMMENT '状态',
    `memo`                        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `role_code`                   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
    `role_name`                   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
    `dept_data_permission_type`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'deptDataPermissionType_user' COMMENT '部门数据权限分类',
    `dept_data_permission_custom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '自定义部门数据权限',
    `default_page`                varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT 'dashboard' COMMENT '默认首页',
    `logical_delete`              bit(1)                                                        NULL     DEFAULT b'1',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_sys_role_role_code` (`role_code` ASC) USING BTREE,
    INDEX `idx_sys_role_role_name` (`role_name` ASC) USING BTREE,
    INDEX `idx_sys_role_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_role_deleted_at` (`deleted_at` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '2023-12-04 13:02:17', 'admin', '2024-01-05 09:51:01', '', NULL, 1, 'yesNo_yes', 'onOff_on',
        '这是超级管理员组，拥有所有权限，请不要编辑！', 'super-admin', '超级管理员组', 'deptDataPermissionType_all', '',
        'dashboard', b'1');
INSERT INTO `sys_role`
VALUES (2, '2024-01-05 00:01:44', 'admin', NULL, NULL, NULL, 2, 'yesNo_no', 'onOff_on', '测试员001', 'test', '测试员',
        'deptDataPermissionType_user', NULL, 'dashboard', b'1');

-- ----------------------------
-- Table structure for sys_role_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_api`;
CREATE TABLE `sys_role_api`
(
    `role_code`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
    `api_group`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Api分组',
    `api_method` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'POST' COMMENT '请求方法',
    `api_path`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Api地址',
    INDEX `idx_sys_role_api_role_code` (`role_code` ASC) USING BTREE,
    INDEX `idx_sys_role_api_api_group` (`api_group` ASC) USING BTREE,
    INDEX `idx_sys_role_api_api_method` (`api_method` ASC) USING BTREE,
    INDEX `idx_sys_role_api_api_path` (`api_path` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_api
-- ----------------------------
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user', 'POST', '/user/get-user-menu');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user', 'POST', '/user/get-user-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user', 'POST', '/user/edit-user');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user', 'POST', '/user/add-user');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user', 'POST', '/user/delete-user-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user', 'POST', '/user/query-user-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user', 'POST', '/user/reset-password');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user', 'POST', '/user/change-password');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user', 'POST', '/user/change-nickname');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/get-role-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/edit-role');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/add-role');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/delete-role-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/query-role-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/get-role-menu-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/edit-role-menu');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/get-role-api-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/edit-role-api');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/query-user-by-role');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/remove-role-user');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/add-role-user');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/edit-role-dept-data-permission');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'role', 'POST', '/role/get-role-button-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'menu', 'POST', '/menu/get-menu-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'menu', 'POST', '/menu/edit-menu');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'menu', 'POST', '/menu/add-menu');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'menu', 'POST', '/menu/delete-menu-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'menu', 'POST', '/menu/query-menu-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dept', 'POST', '/dept/get-dept-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dept', 'POST', '/dept/edit-dept');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dept', 'POST', '/dept/add-dept');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dept', 'POST', '/dept/delete-dept-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dept', 'POST', '/dept/query-dept-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dept', 'POST', '/dept/query-user-by-dept');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dept', 'POST', '/dept/remove-dept-user');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dept', 'POST', '/dept/add-dept-user');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dict', 'POST', '/dict/get-dict-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dict', 'POST', '/dict/edit-dict');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dict', 'POST', '/dict/add-dict');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dict', 'POST', '/dict/delete-dict-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'dict', 'POST', '/dict/query-dict-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'api', 'POST', '/api/get-api-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'api', 'POST', '/api/edit-api');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'api', 'POST', '/api/add-api');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'api', 'POST', '/api/delete-api-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'api', 'POST', '/api/query-api-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'upload', 'POST', '/upload/upload-avatar');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'upload', 'POST', '/upload/upload-file');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'upload', 'POST', '/upload/upload-banner-image');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'upload', 'POST', '/upload/upload-logo');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'upload', 'POST', '/upload/upload-favicon');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'config-backend', 'POST', '/config-backend/get-config-backend-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'config-backend', 'POST', '/config-backend/edit-config-backend');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'config-backend', 'POST', '/config-backend/add-config-backend');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'config-backend', 'POST', '/config-backend/delete-config-backend-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'config-frontend', 'POST', '/config-frontend/get-config-frontend-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'config-frontend', 'POST', '/config-frontend/edit-config-frontend');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'config-frontend', 'POST', '/config-frontend/add-config-frontend');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'config-frontend', 'POST', '/config-frontend/delete-config-frontend-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'log', 'POST', '/log/get-log-login-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'log', 'POST', '/log/delete-log-login-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'log', 'POST', '/log/get-log-operation-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'log', 'POST', '/log/delete-log-operation-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'notice', 'POST', '/notice/get-notice-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'notice', 'POST', '/notice/add-notice');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'notice', 'POST', '/notice/delete-notice-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'notice', 'POST', '/notice/query-notice-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'notice', 'POST', '/notice/query-notice-read-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'notice', 'POST', '/notice/send-notice');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'todo', 'POST', '/todo/get-todo-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'todo', 'POST', '/todo/edit-todo');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'todo', 'POST', '/todo/add-todo');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'todo', 'POST', '/todo/delete-todo-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'todo', 'POST', '/todo/query-todo-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'genPlugin', 'POST', '/gen-plugin/get-gen-plugin-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'genPlugin', 'POST', '/gen-plugin/gen-plugin');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'genPlugin', 'POST', '/gen-plugin/delete-gen-plugin-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'genPlugin', 'POST', '/gen-plugin/download-gen-plugin-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user-online', 'POST', '/user-online/get-user-online-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'user-online', 'POST', '/user-online/kick-online-user');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'cron', 'POST', '/cron/get-cron-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'cron', 'POST', '/cron/start-cron');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'cron', 'POST', '/cron/stop-cron');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-example', 'POST', '/plugin-example/get-test-data-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-example', 'POST', '/plugin-example/edit-test-data');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-example', 'POST', '/plugin-example/add-test-data');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-example', 'POST', '/plugin-example/delete-test-data-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-example', 'POST', '/plugin-example/query-test-data-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-example', 'POST', '/plugin-example/download-template-test-data');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-example', 'POST', '/plugin-example/export-test-data');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-example', 'POST', '/plugin-example/import-test-data');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-achievement', 'POST', '/plugin-achievement/get-category-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-achievement', 'POST', '/plugin-achievement/edit-category');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-achievement', 'POST', '/plugin-achievement/add-category');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-achievement', 'POST', '/plugin-achievement/delete-category-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-achievement', 'POST', '/plugin-achievement/query-category-by-id');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-achievement', 'POST', '/plugin-achievement/get-obtain-list');
INSERT INTO `sys_role_api`
VALUES ('super-admin', 'plugin-achievement', 'POST', '/plugin-achievement/obtain-find');

-- ----------------------------
-- Table structure for sys_role_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_button`;
CREATE TABLE `sys_role_button`
(
    `sys_role_role_code`     varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `sys_button_button_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`sys_role_role_code`, `sys_button_button_code`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_button
-- ----------------------------
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'api:add');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'api:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'api:edit');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'config-backend:add');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'config-backend:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'config-backend:reset');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'config-backend:save');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'config-frontend:add');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'config-frontend:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'config-frontend:reset');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'config-frontend:save');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'dept:addChildren');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'dept:addParent');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'dept:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'dept:deptUser');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'dept:edit');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'dict:addChildren');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'dict:addParent');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'dict:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'dict:edit');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'genPlugin:add');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'genPlugin:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'genPlugin:download');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'genPlugin:gen');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'log-login:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'log-login:detail');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'log-operation:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'log-operation:detail');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'menu:addChildren');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'menu:addParent');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'menu:buttonRegister');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'menu:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'menu:edit');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'notice:add');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'notice:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'notice:send');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'role:add');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'role:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'role:edit');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'role:permission');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'role:user');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'user-online:kick');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'user:add');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'user:delete');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'user:edit');
INSERT INTO `sys_role_button`
VALUES ('super-admin', 'user:password');
INSERT INTO `sys_role_button`
VALUES ('test', 'dept:addChildren');
INSERT INTO `sys_role_button`
VALUES ('test', 'log-login:delete');
INSERT INTO `sys_role_button`
VALUES ('test', 'log-login:detail');
INSERT INTO `sys_role_button`
VALUES ('test', 'log-operation:delete');
INSERT INTO `sys_role_button`
VALUES ('test', 'log-operation:detail');
INSERT INTO `sys_role_button`
VALUES ('test', 'user-online:kick');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `sys_role_role_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `sys_menu_name`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`sys_role_role_code`, `sys_menu_name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'api');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'CiliPluginAchievement');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'CiliPluginExample');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'config-backend');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'config-frontend');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'cron');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'dashboard');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'dept');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'dict');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'genPlugin');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'gitee');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'log');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'log-login');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'log-operation');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'menu');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'notice');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'plugin-achievement-category');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'plugin-achievement-obtain');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'plugin-example-cascader');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'plugin-example-editor');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'plugin-example-icons');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'plugin-example-import-export');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'plugin-example-statistic');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'plugin-example-tree-table');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'plugin-example-uploader');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'role');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'system');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'user');
INSERT INTO `sys_role_menu`
VALUES ('super-admin', 'user-online');
INSERT INTO `sys_role_menu`
VALUES ('test', 'dashboard');
INSERT INTO `sys_role_menu`
VALUES ('test', 'dept');
INSERT INTO `sys_role_menu`
VALUES ('test', 'log');
INSERT INTO `sys_role_menu`
VALUES ('test', 'log-login');
INSERT INTO `sys_role_menu`
VALUES ('test', 'log-operation');
INSERT INTO `sys_role_menu`
VALUES ('test', 'system');
INSERT INTO `sys_role_menu`
VALUES ('test', 'user-online');

-- ----------------------------
-- Table structure for sys_todo
-- ----------------------------
DROP TABLE IF EXISTS `sys_todo`;
CREATE TABLE `sys_todo`
(
    `id`          bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`  datetime                                                      NULL DEFAULT NULL,
    `created_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`  datetime                                                      NULL DEFAULT NULL,
    `updated_by`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`  datetime                                                      NULL DEFAULT NULL,
    `sort`        bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `todo_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '内容',
    `todo_status` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'yesNo_no' COMMENT '状态',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_todo_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_todo_deleted_at` (`deleted_at` ASC) USING BTREE,
    INDEX `idx_sys_todo_todo_status` (`todo_status` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_todo
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`             bigint UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `created_at`     datetime                                                      NULL DEFAULT NULL,
    `created_by`     varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `updated_at`     datetime                                                      NULL DEFAULT NULL,
    `updated_by`     varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deleted_at`     datetime                                                      NULL DEFAULT NULL,
    `sort`           bigint UNSIGNED                                               NULL DEFAULT 1 COMMENT '排序',
    `stable`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'yesNo_no' COMMENT '系统内置',
    `status`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT 'onOff_on' COMMENT '状态',
    `memo`           text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '备注描述',
    `username`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `nickname`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
    `real_name`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
    `password`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
    `avatar`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
    `gender`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'gender_unknown' COMMENT '性别',
    `mobile`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
    `email`          varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `logical_delete` bit(1)                                                        NULL DEFAULT b'1',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_sys_user_username` (`username` ASC) USING BTREE,
    INDEX `idx_sys_user_created_by` (`created_by` ASC) USING BTREE,
    INDEX `idx_sys_user_deleted_at` (`deleted_at` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, '2023-12-04 13:02:17', 'admin', '2024-01-08 20:21:14', 'admin', NULL, 1, 'yesNo_yes', 'onOff_on',
        'Be the change you want to see in the world.', 'admin', 'Mr.C', 'SuperAdmin',
        '$2a$10$jGr2rP6BJdTR7QPx8RNVge2RxcbsJJgWXtUKf2cMz4RATXrx1/6wy',
        '', 'gender_unknown',
        '1234567890', '11111111111', b'1');
INSERT INTO `sys_user`
VALUES (2, '2024-01-05 00:03:39', 'admin', '2024-01-05 00:03:47', 'admin', NULL, NULL, NULL, 'onOff_on', '测试人员',
        'test001', '爱修bug的小猫', '小晨晨', '$2a$10$59cQPuEzwzoabaDh35ZtY.r7qFyImiAhcD2.YnBXgvAyFiHB6Weo.', NULL,
        'gender_male', '19882026235', '2332869185@qq.com', b'1');

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`
(
    `username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `token`    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
    UNIQUE INDEX `idx_sys_user_online_username` (`username` ASC) USING BTREE,
    UNIQUE INDEX `username` (`username` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `sys_role_role_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '\'角色编码\'',
    `sys_user_username`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '\'用户名\'',
    PRIMARY KEY (`sys_role_role_code`, `sys_user_username`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES ('super-admin', 'admin');
INSERT INTO `sys_user_role`
VALUES ('test', 'test001');

SET FOREIGN_KEY_CHECKS = 1;
