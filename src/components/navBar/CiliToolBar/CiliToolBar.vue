<template>
  <q-toolbar class="q-pt-sm">
    <q-space/>
    <q-btn v-if="$q.screen.gt.xs" :ripple="false" class="q-ml-xs no-pointer-events" fab-mini flat no-caps no-wrap>
      <q-icon color="red" name="img:logo.svg" size="28px"/>
      <q-toolbar-title class="text-weight-bold" shrink>
        {{ $t('Slogan') }}
      </q-toolbar-title>
    </q-btn>
    <div :class="$q.screen.lt.sm?'q-gutter-xs-sm col items-center no-wrap':'q-gutter-xs-sm row items-center no-wrap'">
      <q-btn fab-mini flat to="/">
        {{ $t('Index') }}
      </q-btn>
      <q-btn fab-mini flat to="/course">
        {{ $t('Course') }}
      </q-btn>
    </div>
    <div class="YL__toolbar-input-container row no-wrap q-mr-sm">
      <q-input
        v-model="search"
        :placeholder="$t('KeyWord')" clearable debounce="500" dense standout="bg-grey-7 text-white">
        <template v-slot:prepend>
          <q-btn flat icon="search" padding="none" rounded size="md" unelevated/>
        </template>
      </q-input>
    </div>
    <div class="q-gutter-xs row  items-center no-wrap">

      <CiliPopover :click-event="showLoginFrom" :label="$t('Login')" clClass="shadow-up-3"
                   clStyle="background: #2093da; color: white" to="/keceng">
        <template #default>
          <div class="v-popover is-bottom">
            <div class="v-popover-content">
              <div :class="darkTheme" class="login-panel-popover"><p class="title">{{ $t('LoginAndYouCan') }}</p>
                <div class="login-tip-content">
                  <div class="login-tip-content-item">
                    <svg class="login-tip-content-item-icon" fill="currentColor"
                         height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"
                         xmlns:xlink="http://www.w3.org/1999/xlink">
                      <path
                        d="M12 4.99805C9.48178 4.99805 7.283 5.12616 5.73089 5.25202C4.65221 5.33949 3.81611 6.16352 3.72 7.23254C3.60607 8.4998 3.5 10.171 3.5 11.998C3.5 13.8251 3.60607 15.4963 3.72 16.76355C3.81611 17.83255 4.65221 18.6566 5.73089 18.7441C7.283 18.8699 9.48178 18.998 12 18.998C14.5185 18.998 16.7174 18.8699 18.2696 18.74405C19.3481 18.65655 20.184 17.8328 20.2801 16.76405C20.394 15.4973 20.5 13.82645 20.5 11.998C20.5 10.16965 20.394 8.49877 20.2801 7.23205C20.184 6.1633 19.3481 5.33952 18.2696 5.25205C16.7174 5.12618 14.5185 4.99805 12 4.99805zM5.60965 3.75693C7.19232 3.62859 9.43258 3.49805 12 3.49805C14.5677 3.49805 16.8081 3.62861 18.3908 3.75696C20.1881 3.90272 21.6118 5.29278 21.7741 7.09773C21.8909 8.3969 22 10.11405 22 11.998C22 13.88205 21.8909 15.5992 21.7741 16.8984C21.6118 18.7033 20.1881 20.09335 18.3908 20.23915C16.8081 20.3675 14.5677 20.498 12 20.498C9.43258 20.498 7.19232 20.3675 5.60965 20.2392C3.81206 20.0934 2.38831 18.70295 2.22603 16.8979C2.10918 15.5982 2 13.8808 2 11.998C2 10.1153 2.10918 8.39787 2.22603 7.09823C2.38831 5.29312 3.81206 3.90269 5.60965 3.75693z"
                        fill="currentColor"></path>
                      <path
                        d="M6.85626 8.28906C7.27047 8.28906 7.60626 8.62485 7.60626 9.03906L7.60626 11.25L10.43895 11.25L10.43895 9.03906C10.43895 8.62485 10.7747 8.28906 11.18895 8.28906C11.6031 8.28906 11.93895 8.62485 11.93895 9.03906L11.93895 14.96095C11.93895 15.37515 11.6031 15.71095 11.18895 15.71095C10.7747 15.71095 10.43895 15.37515 10.43895 14.96095L10.43895 12.75L7.60626 12.75L7.60626 14.96095C7.60626 15.37515 7.27047 15.71095 6.85626 15.71095C6.44205 15.71095 6.10626 15.37515 6.10626 14.96095L6.10626 9.03906C6.10626 8.62485 6.44205 8.28906 6.85626 8.28906z"
                        fill="currentColor"></path>
                      <path
                        d="M14.5492 9.80269L14.5492 14.19735C15.1834 14.17465 15.76965 14.1047 16.2202 13.87005C16.47645 13.7365 16.6803 13.55365 16.82725 13.28815C16.97795 13.016 17.09375 12.6113 17.09375 12C17.09375 11.38875 16.97795 10.98405 16.82725 10.7119C16.6803 10.4464 16.47645 10.26359 16.2202 10.12998C15.76965 9.89535 15.1834 9.8254 14.5492 9.80269zM13.0492 9.28909C13.0492 8.73971 13.4943 8.28713 14.05195 8.28944C14.86295 8.29268 15.99115 8.31782 16.9166 8.80148C17.4017 9.05504 17.83555 9.43502 18.1409 9.98772C18.4426 10.5338 18.59375 11.20265 18.59375 12C18.59375 12.79745 18.4426 13.46625 18.1409 14.01235C17.83555 14.565 17.4017 14.945 16.9166 15.1986C15.99115 15.6822 14.86295 15.70735 14.05195 15.7106C13.4943 15.7129 13.0492 15.26035 13.0492 14.71095L13.0492 9.28909z"
                        fill="currentColor"></path>
                    </svg>
                    <span :class="darkTheme" class="lt-text">{{ $t('WatchFree') }}</span></div>
                  <div class="login-tip-content-item">
                    <svg class="login-tip-content-item-icon" fill="currentColor"
                         height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"
                         xmlns:xlink="http://www.w3.org/1999/xlink">
                      <path
                        d="M12 7.23438C12.4142 7.23438 12.75 7.57017 12.75 7.98438L12.75 11.68935L15.40535 14.34465C15.6982 14.63755 15.6982 15.11245 15.40535 15.40535C15.11245 15.6982 14.63755 15.6982 14.34465 15.40535L11.46965 12.53035C11.32905 12.3897 11.25 12.1989 11.25 12L11.25 7.98438C11.25 7.57017 11.5858 7.23438 12 7.23438z"
                        fill="currentColor"></path>
                      <path
                        d="M12 3.5C7.30558 3.5 3.5 7.30558 3.5 12C3.5 16.6944 7.30558 20.5 12 20.5C16.6944 20.5 20.5 16.6944 20.5 12C20.5 7.30558 16.6944 3.5 12 3.5zM2 12C2 6.47715 6.47715 2 12 2C17.52285 2 22 6.47715 22 12C22 17.52285 17.52285 22 12 22C6.47715 22 2 17.52285 2 12z"
                        fill="currentColor"></path>
                    </svg>
                    <span :class="darkTheme" class="lt-text">{{ $t('MultiEnd') }}</span></div>
                  <div class="login-tip-content-item">
                    <svg class="login-tip-content-item-icon" fill="currentColor"
                         height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"
                         xmlns:xlink="http://www.w3.org/1999/xlink">
                      <path
                        d="M12 4.99805C9.48178 4.99805 7.283 5.12616 5.73089 5.25202C4.65221 5.33949 3.81611 6.16352 3.72 7.23254C3.60607 8.4998 3.5 10.171 3.5 11.998C3.5 13.8251 3.60607 15.4963 3.72 16.76355C3.81611 17.83255 4.65221 18.6566 5.73089 18.7441C7.283 18.8699 9.48178 18.998 12 18.998C14.5185 18.998 16.7174 18.8699 18.2696 18.74405C19.3481 18.65655 20.184 17.8328 20.2801 16.76405C20.394 15.4973 20.5 13.82645 20.5 11.998C20.5 10.16965 20.394 8.49877 20.2801 7.23205C20.184 6.1633 19.3481 5.33952 18.2696 5.25205C16.7174 5.12618 14.5185 4.99805 12 4.99805zM5.60965 3.75693C7.19232 3.62859 9.43258 3.49805 12 3.49805C14.5677 3.49805 16.8081 3.62861 18.3908 3.75696C20.1881 3.90272 21.6118 5.29278 21.7741 7.09773C21.8909 8.3969 22 10.11405 22 11.998C22 13.88205 21.8909 15.5992 21.7741 16.8984C21.6118 18.7033 20.1881 20.09335 18.3908 20.23915C16.8081 20.3675 14.5677 20.498 12 20.498C9.43258 20.498 7.19232 20.3675 5.60965 20.2392C3.81206 20.0934 2.38831 18.70295 2.22603 16.8979C2.10918 15.5982 2 13.8808 2 11.998C2 10.1153 2.10918 8.39787 2.22603 7.09823C2.38831 5.29312 3.81206 3.90269 5.60965 3.75693z"
                        fill="currentColor"></path>
                      <path
                        d="M15.875 9.25L9.875 9.25C9.46079 9.25 9.125 8.91421 9.125 8.5C9.125 8.08579 9.46079 7.75 9.875 7.75L15.875 7.75C16.2892 7.75 16.625 8.08579 16.625 8.5C16.625 8.91421 16.2892 9.25 15.875 9.25z"
                        fill="currentColor"></path>
                      <path
                        d="M17.375 12.75L11.375 12.75C10.9608 12.75 10.625 12.4142 10.625 12C10.625 11.5858 10.9608 11.25 11.375 11.25L17.375 11.25C17.7892 11.25 18.125 11.5858 18.125 12C18.125 12.4142 17.7892 12.75 17.375 12.75z"
                        fill="currentColor"></path>
                      <path
                        d="M7.875 8.5C7.875 8.91421 7.53921 9.25 7.125 9.25L6.625 9.25C6.21079 9.25 5.875 8.91421 5.875 8.5C5.875 8.08579 6.21079 7.75 6.625 7.75L7.125 7.75C7.53921 7.75 7.875 8.08579 7.875 8.5z"
                        fill="currentColor"></path>
                      <path
                        d="M9.375 12C9.375 12.4142 9.03921 12.75 8.625 12.75L8.125 12.75C7.71079 12.75 7.375 12.4142 7.375 12C7.375 11.5858 7.71079 11.25 8.125 11.25L8.625 11.25C9.03921 11.25 9.375 11.5858 9.375 12z"
                        fill="currentColor"></path>
                    </svg>
                    <span :class="darkTheme" class="lt-text">{{ $t('PostABarrage') }}</span></div>
                  <div class="login-tip-content-item">
                    <svg class="login-tip-content-item-icon" fill="currentColor"
                         height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"
                         xmlns:xlink="http://www.w3.org/1999/xlink">
                      <path
                        d="M12 4.99805C9.48178 4.99805 7.283 5.12616 5.73089 5.25202C4.65221 5.33949 3.81611 6.16352 3.72 7.23254C3.60607 8.4998 3.5 10.171 3.5 11.998C3.5 13.8251 3.60607 15.4963 3.72 16.76355C3.81611 17.83255 4.65221 18.6566 5.73089 18.7441C7.283 18.8699 9.48178 18.998 12 18.998C14.5185 18.998 16.7174 18.8699 18.2696 18.74405C19.3481 18.65655 20.184 17.8328 20.2801 16.76405C20.394 15.4973 20.5 13.82645 20.5 11.998C20.5 10.16965 20.394 8.49877 20.2801 7.23205C20.184 6.1633 19.3481 5.33952 18.2696 5.25205C16.7174 5.12618 14.5185 4.99805 12 4.99805zM5.60965 3.75693C7.19232 3.62859 9.43258 3.49805 12 3.49805C14.5677 3.49805 16.8081 3.62861 18.3908 3.75696C20.1881 3.90272 21.6118 5.29278 21.7741 7.09773C21.8909 8.3969 22 10.11405 22 11.998C22 13.88205 21.8909 15.5992 21.7741 16.8984C21.6118 18.7033 20.1881 20.09335 18.3908 20.23915C16.8081 20.3675 14.5677 20.498 12 20.498C9.43258 20.498 7.19232 20.3675 5.60965 20.2392C3.81206 20.0934 2.38831 18.70295 2.22603 16.8979C2.10918 15.5982 2 13.8808 2 11.998C2 10.1153 2.10918 8.39787 2.22603 7.09823C2.38831 5.29312 3.81206 3.90269 5.60965 3.75693z"
                        fill="currentColor"></path>
                      <path
                        d="M14.7138 10.96875C15.50765 11.4271 15.50765 12.573 14.71375 13.0313L11.5362 14.8659C10.74235 15.3242 9.75 14.7513 9.75001 13.8346L9.75001 10.1655C9.75001 9.24881 10.74235 8.67587 11.5362 9.13422L14.7138 10.96875z"
                        fill="currentColor"></path>
                    </svg>
                    <span :class="darkTheme" class="lt-text">{{ $t('Watching') }}</span></div>
                </div>
                <div class="login-btn" @click="showLoginFrom">{{ $t('SignInNow') }}</div>
                <div :class="darkTheme" class="register-tip"> {{ $t('FirstTimeUse') }}<span
                  class="register-exper">  {{ $t('ClickMeToRegiste') }}</span></div>
              </div>
            </div>
          </div>
        </template>
      </CiliPopover>


      <CiliPopover v-if="$q.screen.gt.sm" :label="$t('Notice')" name="notifications">
        <template #badge>
          <q-badge color="red" floating text-color="white">
            2
          </q-badge>
        </template>
        <template #default>
          <div class="v-popover is-bottom">
            <div :class="darkTheme" class="v-popover-content">
              <div class="login-panel-popover "><p :class="darkTheme" class="tips">{{
                  $t('LoginToViewMessageRecords')
                }}</p>
                <div class="login-btn" @click="showLoginFrom"> {{ $t('SignInNow') }}</div>
              </div>
            </div>
          </div>
        </template>
      </CiliPopover>

      <CiliPopover v-if="$q.screen.gt.sm" :label="$t('Collect')" name="hotel_class">
        <template #default>
          <div class="v-popover is-bottom">
            <div :class="darkTheme" class="v-popover-content">
              <div class="login-panel-popover"><p :class="darkTheme" class="tips">{{
                  $t('LoginToViewMyCollection')
                }}</p>
                <div class="login-btn" @click="showLoginFrom"> {{ $t('SignInNow') }}</div>
              </div>
            </div>
          </div>
        </template>
      </CiliPopover>

      <CiliPopover v-if="$q.screen.gt.sm" :label="$t('Records')" name="access_time">
        <div class="v-popover is-bottom">
          <div :class="darkTheme" class="v-popover-content">
            <div class="login-panel-popover"><p :class="darkTheme" class="tips">{{ $t('LoginToViewHistory') }}</p>
              <div class="login-btn" @click="showLoginFrom"> {{ $t('SignInNow') }}</div>
            </div>
          </div>
        </div>
      </CiliPopover>

      <CiliPopover :label="$t('Contribute')" name="cloud_upload">
        <div class="">
          <div class="">
            <div class=" upload-panel-popover">
              <div class="upload-item">
                <svg class="item-icon" fill="none" height="26" viewBox="0 0 26 26" width="26"
                     xmlns="http://www.w3.org/2000/svg">
                  <rect fill="#C4C4C4" height="24" opacity="0.01" width="24" x="1.55603"></rect>
                  <path clip-rule="evenodd"
                        d="M21.556 10.1866C21.0037 10.1866 20.556 10.6343 20.556 11.1866V12.5712C20.556 13.1234 21.0037 13.5712 21.556 13.5712C22.1083 13.5712 22.556 13.1234 22.556 12.5712V11.1866C22.556 10.6343 22.1083 10.1866 21.556 10.1866ZM14.556 20.5C14.0037 20.5 13.556 20.9477 13.556 21.5C13.556 22.0523 14.0037 22.5 14.556 22.5H15.556C16.1083 22.5 16.556 22.0523 16.556 21.5C16.556 20.9477 16.1083 20.5 15.556 20.5H14.556Z"
                        fill="var(--text2)"
                        fill-rule="evenodd"></path>
                  <path clip-rule="evenodd"
                        d="M22.6093 15.348C22.0235 14.7622 21.0738 14.7622 20.488 15.348L18.0131 17.8229C17.6226 18.2134 17.6226 18.8465 18.0131 19.2371C18.4037 19.6276 19.0368 19.6276 19.4274 19.2371L21.5487 17.1158L23.67 19.2371C24.0605 19.6276 24.6937 19.6276 25.0842 19.2371C25.4747 18.8465 25.4747 18.2134 25.0842 17.8229L22.6093 15.348Z"
                        fill="var(--text2)"
                        fill-rule="evenodd"></path>
                  <path clip-rule="evenodd"
                        d="M21.5487 14.9759C22.101 14.9759 22.5487 15.4236 22.5487 15.9759L22.5487 22.5C22.5487 23.0523 22.101 23.5 21.5487 23.5C20.9964 23.5 20.5487 23.0523 20.5487 22.5L20.5487 15.9759C20.5487 15.4236 20.9964 14.9759 21.5487 14.9759Z"
                        fill="var(--text2)"
                        fill-rule="evenodd"></path>
                  <path clip-rule="evenodd"
                        d="M3.20868 5.5H19.556C20.1083 5.5 20.556 5.94771 20.556 6.5V11.8911H22.556V6.5C22.556 4.84315 21.2129 3.5 19.556 3.5H3.20868C1.55183 3.5 0.208679 4.84315 0.208679 6.5V19.5C0.208679 21.1569 1.55182 22.5 3.20868 22.5H15.453V20.5H3.20868C2.65639 20.5 2.20868 20.0523 2.20868 19.5V6.5C2.20868 5.94772 2.6564 5.5 3.20868 5.5Z"
                        fill="var(--text2)"
                        fill-rule="evenodd"></path>
                  <path
                    d="M13.3617 12.134C14.0284 12.5189 14.0284 13.4811 13.3617 13.866L10.3617 15.5981C9.69503 15.983 8.86169 15.5019 8.86169 14.7321V11.2679C8.86169 10.4981 9.69503 10.017 10.3617 10.4019L13.3617 12.134Z"
                    fill="var(--text2)"></path>
                </svg>
                <div class="item-title"> {{ $t('VideoSubmission') }}</div>
              </div>
              <div class="upload-item">
                <svg class="item-icon" fill="none" height="26" viewBox="0 0 26 26" width="26"
                     xmlns="http://www.w3.org/2000/svg">
                  <rect fill="#C4C4C4" height="24" opacity="0.01" width="24" x="1.5" y="1"></rect>
                  <path clip-rule="evenodd"
                        d="M16 3.5H6C5.72386 3.5 5.5 3.72386 5.5 4V21C5.5 21.2761 5.72386 21.5 6 21.5H10.5H11.5C12.0523 21.5 12.5 21.9477 12.5 22.5C12.5 23.0523 12.0523 23.5 11.5 23.5H10.5H6C4.61929 23.5 3.5 22.3807 3.5 21V4C3.5 2.61929 4.61929 1.5 6 1.5H16C19.5899 1.5 22.5 4.41015 22.5 8V11.2806V11.818V12.2806C22.5 12.8329 22.0523 13.2806 21.5 13.2806C20.9477 13.2806 20.5 12.8329 20.5 12.2806V11.818V11.2806V8C20.5 5.51472 18.4853 3.5 16 3.5ZM15 10.634C15.6667 11.0189 15.6667 11.9811 15 12.366L12 14.0981C11.3333 14.483 10.5 14.0019 10.5 13.2321V9.76795C10.5 8.99815 11.3333 8.51702 12 8.90192L15 10.634ZM23.9227 20.238C24.2799 19.6192 24.2799 18.8568 23.9227 18.238L22.3274 15.4748C21.9701 14.856 21.3098 14.4748 20.5953 14.4748H17.4047C16.6902 14.4748 16.0299 14.856 15.6727 15.4748L14.0774 18.238C13.7201 18.8568 13.7201 19.6192 14.0774 20.238L15.6727 23.0011C16.0299 23.6199 16.6902 24.0011 17.4047 24.0011H20.5953C21.3098 24.0011 21.9701 23.6199 22.3274 23.0011L23.9227 20.238ZM17.2604 16.7248C17.3497 16.5701 17.5148 16.4748 17.6934 16.4748H20.3066C20.4853 16.4748 20.6503 16.5701 20.7397 16.7248L22.0463 18.988C22.1356 19.1427 22.1356 19.3333 22.0463 19.488L20.7397 21.7511C20.6503 21.9058 20.4853 22.0011 20.3066 22.0011H17.6934C17.5148 22.0011 17.3497 21.9058 17.2604 21.7511L15.9538 19.488C15.8644 19.3333 15.8644 19.1427 15.9538 18.988L17.2604 16.7248ZM19 17.9879C18.3096 17.9879 17.75 18.5476 17.75 19.2379C17.75 19.9283 18.3096 20.4879 19 20.4879C19.6904 20.4879 20.25 19.9283 20.25 19.2379C20.25 18.5476 19.6904 17.9879 19 17.9879Z"
                        fill="var(--text2)"
                        fill-rule="evenodd"></path>
                </svg>
                <div class="item-title"> {{ $t('SubmissionManagement') }}</div>
              </div>
            </div>
          </div>
        </div>
      </CiliPopover>
    </div>
    <q-space/>
  </q-toolbar>
  <CiliLoginFrom ref="LoginDialog"/>
</template>

<script setup>
import {ref} from 'vue'
import CiliPopover from "../CiliPopover/CiliPopover.vue"
import useTheme from "src/composables/useTheme"
import CiliLoginFrom from 'src/components/CiliLoginFrom/CiliLoginFrom.vue'

const {darkTheme} = useTheme()
const search = ref('')
const LoginDialog = ref(null)
const showLoginFrom = () => {
  console.log(LoginDialog.value.showLoginFrom())
}
</script>

<style lang="sass" scoped>
@import "src/css/quasar.variables.scss"
//.YL
//
//  &__toolbar-input-container
//    min-width: 100px
//    width: 55%
//
//    &:hover
//      color: #000

.upload-item
  path
    fill: $text2
</style>
